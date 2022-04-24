package com.example.upload_ziguang.gb1400.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Gpxx
 * @Date 2022/4/22 11:38
 */
//@Component
//public class KafkaProducer implements CommandLineRunner {
//
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    @Override
//    public void run(String... args) {
//        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() ->
//                {
//                    kafkaTemplate.send("test", "------------------------------------------")
//                            .addCallback(result -> {
//                                if (null != result.getRecordMetadata()) {
//                                    System.out.println("消费发送成功 offset:" + result.getRecordMetadata().offset());
//                                    return;
//                                }
//                                System.out.println("消息发送成功");
//                            }, throwable -> System.out.println("消费发送失败:" + throwable.getMessage()));
//                },
//                0, 1, TimeUnit.SECONDS);
//    }
//}

