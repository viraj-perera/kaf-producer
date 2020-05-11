package com.vipe.kafka.web;


import com.vipe.kafka.config.KafkaTopicConfig;
import com.vipe.kafka.pub.AppNotificationPublisher;
import com.vipe.kafka.pub.Notification;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

@RestController(value = "TestController")
@RequestMapping(value = "/api/test")
public class TestController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private AppNotificationPublisher notificationPublisher;

    @GetMapping(value = "/{message}", produces = { "application/json"})
    public ResponseEntity getPersonInfo(@PathVariable("message") String message) {
        Notification notification = new Notification(12l, message, Notification.Type.ALERT);
        notification.setId(2563L);

        for (int i=0; i<2500; i++){
            sendMessage(message+ " -> "+i);
        }
        return new ResponseEntity(notification, HttpStatus.OK);
    }

    private void sendMessage(String message) {

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(KafkaTopicConfig.topic1, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }

}
