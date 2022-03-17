package com.example.upload_ziguang.gb1400.web;


import com.example.upload_ziguang.gb1400.util.Global;
import com.example.upload_ziguang.gb1400.web.gb1400.CarService;
import com.example.upload_ziguang.gb1400.web.gb1400.FaceService;
import com.example.upload_ziguang.gb1400.web.gb1400.KeepAliveService;
import com.example.upload_ziguang.gb1400.web.gb1400.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

@Slf4j
@Component
@EnableScheduling
public class TimeController {
    @Autowired
    private FaceService faceService;
    @Autowired
    private CarService carService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private KeepAliveService keepAliveService;
    @Autowired
    private SendService sendService;
    @Value("${this.upload_hai_kang}")
    private boolean uploadHaiKang;
    @Value("${this.upload_car}")
    private boolean upload_car;
    @Value("${this.upload_face}")
    private boolean upload_face;
    @Value("${this.register}")
    private boolean register;
    @Value("${this.keepAlive}")
    private boolean keepAlive;
    public static ThreadPoolExecutor threadPoolExecutor;

    static {
        threadPoolExecutor = new ThreadPoolExecutor(
                100,
                100,
                10L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    @PostConstruct
    public void register() {
        if (!register) {
            return;
        }
        //先注册
        CountDownLatch register = new CountDownLatch(Global.device_ids.length);
        for (int i = 0; i < Global.device_ids.length; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> {
                registerService.send(null, Global.device_ids[finalI]);
                register.countDown();
            });
        }
        try {
            register.await();
        } catch (InterruptedException e) {
            log.error("注册时 等待异常");

        }
    }

    @Scheduled(cron = "${SEND_YI_SA_CAR}")
    public void senCar() {
        if (upload_car) {
            carService.sendCar();
        }
    }

    @Scheduled(cron = "${SEND_YI_SA_FACE}")
    public void sendFace() {
        if (upload_face) {
            faceService.sendFace();
        }
    }

    @Scheduled(cron = "${SEND_HAI_KANG}")
    public void sendHaiKang() {
        if (uploadHaiKang) {
//            sendService.sendHaiKang();
        }
    }

    @Scheduled(fixedRate = 9 * 10000)
    public void keepAlive() {
        if (!keepAlive) {
            return;
        }
        CountDownLatch keepAlive = new CountDownLatch(Global.device_ids.length);
        for (int i = 0; i < Global.device_ids.length; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> {
                keepAliveService.keepAlive(Global.device_ids[finalI]);
                keepAlive.countDown();
            });
        }
        try {
            keepAlive.await();
        } catch (InterruptedException e) {
            log.error("注册时 等待异常");

        }
    }


}
