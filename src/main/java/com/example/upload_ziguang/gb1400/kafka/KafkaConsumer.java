package com.example.upload_ziguang.gb1400.kafka;

import com.example.upload_ziguang.gb1400.domain.TempData;
import com.example.upload_ziguang.gb1400.mapper.TempMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author Gpxx
 * @Date 2022/4/22 11:38
 */
@Component@Slf4j
public class KafkaConsumer {
@Autowired
private TempMapper tempMapper;

    @KafkaListener(topics = "alarm-forward-event",groupId = "kafka-springboot-001")
    public void consumer(ConsumerRecord<String, String> record, Acknowledgment ack) throws InterruptedException {
//        log.info("收到的数据--->{}",record.value());
        TempData tempData=new TempData();
        tempData.setData(record.value());
        tempData.setUpload(0);
        tempMapper.insert(tempData);
        log.info("收到预警数据");

        ack.acknowledge();
    }
}
