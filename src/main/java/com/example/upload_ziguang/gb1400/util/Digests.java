package com.example.upload_ziguang.gb1400.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.Validate;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2019/7/16
 * Time 15:26
 */
public class Digests {
    private static SecureRandom random = new SecureRandom();
    /**
     * 加密遵循RFC2671规范 将相关参数加密生成一个MD5字符串,并返回
     */
    public static String http_da_calc_HA1(String username, String realm, String password,
                                          String nonce, String nc, String cnonce, String qop,
                                          String method, String uri, String algorithm) {
        String HA1, HA2;
        if ("MD5-sess".equals(algorithm)) {
            HA1 = HA1_MD5_sess(username, realm, password, nonce, cnonce);
        } else {
            HA1 = HA1_MD5(username, realm, password);
        }
        byte[] md5Byte = md5(HA1.getBytes());
        HA1 = new String(Hex.encodeHex(md5Byte));

        md5Byte = md5(HA2(method, uri).getBytes());
        HA2 = new String(Hex.encodeHex(md5Byte));

        String original = HA1 + ":" + (nonce + ":" + nc + ":" + cnonce + ":" + qop) + ":" + HA2;

        md5Byte = md5(original.getBytes());
        return new String(Hex.encodeHex(md5Byte));

    }

    /**
     * algorithm值为MD5时规则
     */
    private static String HA1_MD5(String username, String realm, String password) {
        return username + ":" + realm + ":" + password;
    }

    /**
     * algorithm值为MD5-sess时规则
     */
    private static String HA1_MD5_sess(String username, String realm, String password, String nonce, String cnonce) {
        //      MD5(username:realm:password):nonce:cnonce

        String s = HA1_MD5(username, realm, password);
        byte[] md5Byte = md5(s.getBytes());
        String smd5 = new String(Hex.encodeHex(md5Byte));

        return smd5 + ":" + nonce + ":" + cnonce;
    }

    private static String HA2(String method, String uri) {
        return method + ":" + uri;
    }

    /**
     * 对输入字符串进行md5散列.
     */
    public static byte[] md5(byte[] input) {
        return digest(input, "MD5", null, 1);
    }

    /**
     * 对字符串进行散列, 支持md5与sha1算法.
     */
    private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            if (salt != null) {
                digest.update(salt);
            }

            byte[] result = digest.digest(input);

            for (int i = 1; i < iterations; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 随机生成numBytes长度数组
     * @param numBytes
     * @return
     */
    public static byte[] generateSalt(int numBytes) {
        Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", (long)numBytes);
        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    public static String generateSalt2(int length) {
        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2)%2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val.toLowerCase();
    }


}

class test{
    public static void main(String[] args) {
        String s = Digests.http_da_calc_HA1("xunluoche", "viid", "xunluoche", "e279a523522eefc2647953ddccbd56b6",
                "00000001", "223t9201", "auth", "post", "http://44.144.6.38:9080/VIID/System/Register", "MD5");
        System.out.println(s);
    }
}