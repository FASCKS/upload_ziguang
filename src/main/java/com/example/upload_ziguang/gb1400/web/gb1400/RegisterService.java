package com.example.upload_ziguang.gb1400.web.gb1400;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.example.upload_ziguang.gb1400.domain.GB1400.Register;
import com.example.upload_ziguang.gb1400.domain.GB1400.RegisterObject;
import com.example.upload_ziguang.gb1400.domain.GB1400.ResponseStatusOneObject;
import com.example.upload_ziguang.gb1400.util.Digests;
import com.example.upload_ziguang.gb1400.util.Global;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 *
 *
 *
 *
 */
@Slf4j
@Component
public class RegisterService {
    @Value("${YI_SA_IP}")
    private String yiSaIp;
    @Value("${YI_SA_PORT}")
    private String yiSaPort;
     int nc = 0;    //调用次数

    /**
     * 注册流程描述如下：
     * S1：1：发起方向接收方发送注册HTTP POST请求/VIID/System/Register。
     * S2 ： 2 ： 接 收 方 向 发 送 方 发 送 响 应 401 Unauthorized ， 并 在 响 应 的 消 息 头
     * WWW-Authenticate 字段中给出适合发送方的认证体制和参数。
     * S3：3：发起方重新向接收方发送注册HTTP POST请求，在请求的Authorization字段给
     * 出信任书，包含认证信息。
     * S4：4：接收方对请求进行验证，如果检查出发起方身份合法，向发起方发送成功响应
     * 200 OK，如果身份不合法则发送拒绝服务应答。
     */
    public void send(String authorization,String device) {
        String url = "http://" + yiSaIp + ":" + yiSaPort + "/VIID/System/Register";
        nc++;
        log.info("当前nc的值为 {} ", nc);
        {
            RegisterObject registerObject = new RegisterObject();
            Register register = new Register();
            register.setDeviceID(device);
            registerObject.setRegisterObject(register);
            HttpRequest httpRequest =
                    HttpRequest.post(url)
                            .body(JSONUtil.toJsonStr(registerObject))
                            .header(Header.ACCEPT,"*/*")
                            .header(Header.USER_AGENT,"PostmanRuntime/7.28.4")
                            .header(Header.ACCEPT_ENCODING,"gzip, deflate, br")
                            .contentType("application/vvid+json");

            if (authorization != null) {
                httpRequest.header("Authorization", authorization);
            }
            log.info("发送--》{}",httpRequest);
            HttpResponse execute = httpRequest.execute();
            try {
                ResponseStatusOneObject responseStatus = JSONUtil.toBean(execute.body(), ResponseStatusOneObject.class);
                log.info("当前状态 {} ", responseStatus.getResponseStatusObject().getStatusCode());
            }catch (Exception e){
                log.error("对方返回的json解析失败。");
                log.error(e.getMessage(),e);
            }
            int statusCode = execute.getStatus();

            if (statusCode == 401) {
                log.info("准备进入第二次访问");
            }
            if (statusCode == 200) {
                nc = 0;
                log.info("---------------->注册成功<----------------------");
            }
            if (statusCode == 401) {
                String two_authorization = execute.header("WWW-Authenticate");
                log.info("返回的WWW-Authenticate {} ", two_authorization);
                try {
                    log.info("以萨提供的用户名 {}, 密码 {}.", Global.USERNAMEMAP.get(device), Global.PASSWORDMAP.get(device));
                    String postAuthorization = getAuthorization(two_authorization, "/VIID/System/Register",
                            Global.USERNAMEMAP.get(device), Global.PASSWORDMAP.get(device),
                            "POST",nc);
                    log.info("生成授权信息得到的 WWW-Authenticate ----->{}", postAuthorization);
                    if (authorization == null) {
                        this.send(postAuthorization,device);
                    }

                } catch (IOException e) {
                    log.error("获取授权信息错误.");
                    nc = 0;
                    e.printStackTrace();
                }
            }

            nc = 0;

        }

    }

    /**
     * 生成授权信息
     *
     * @param authorization 上一次调用返回401的WWW-Authenticate数据
     * @param username      用户名
     * @param password      密码
     * @return 授权后的数据, 应放在http头的Authorization里
     * @throws IOException 异常
     */
    private static String getAuthorization(String authorization, String uri, String username, String password, String method,Integer nc) throws IOException {

        uri = StrUtil.isEmpty(uri) ? "/" : uri;
        String temp = authorization.replaceFirst("Digest", "").trim()
                .replace("MD5", "\"MD5\"");
        String json = withdrawJson(authorization);
        JSONObject jsonObject = JSON.parseObject(json);
        String cnonce = Digests.generateSalt2(8);
        String ncstr = ("00000000" + nc).substring(Integer.toString(nc).length());     //认证的次数,第一次是1，第二次是2...
        String algorithm = jsonObject.getString("algorithm");
        String qop = jsonObject.getString("qop");
        String nonce = jsonObject.getString("nonce");
        String realm = jsonObject.getString("realm");

        String response = Digests.http_da_calc_HA1(username, realm, password,
                nonce, ncstr, cnonce, qop,
                method, uri, algorithm);

        //组成响应authorization
        authorization = "Digest username=\"" + username +
                "\", realm=" + "\""+realm+"\""+
                ", nonce="+"\""+nonce+"\"" +
                ", algorithm="+"\"MD5\"" +
                ", qop="+qop;
        authorization += ", uri=\"" + uri
                + "\", nc=" + ncstr
                + ", cnonce=\"" + cnonce
                + "\", response=\"" + response + "\"";
        return authorization;
    }

    /**
     * 将返回的Authrization信息转成json
     *
     * @param authorization authorization info
     * @return 返回authrization json格式数据 如：String json = "{ \"realm\": \"Wowza\", \" domain\": \"/\", \" nonce\": \"MTU1NzgxMTU1NzQ4MDo2NzI3MWYxZTZkYjBiMjQ2ZGRjYTQ3ZjNiOTM2YjJjZA==\", \" algorithm\": \"MD5\", \" qop\": \"auth\" }";
     */
    private static String withdrawJson(String authorization) {
        String temp = authorization.replaceFirst("Digest", "").trim().replaceAll("\"", "");
        String[] split = temp.split(",");
        Map<String, String> map = new HashMap<>();
        Arrays.asList(split).forEach(c -> {
            String c1 = c.replaceFirst("=", ":");
            String[] split1 = c1.split(":");
            map.put(split1[0].trim(), split1[1].trim());
        });
        return JSONObject.toJSONString(map);
    }
}


