package com.vipe.kafka.web;


import com.vipe.kafka.config.KafkaTopicConfig;
import com.vipe.kafka.type.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "StringMessageTestController")
@RequestMapping(value = "/api/str/")
public class StringMessageTestController {

    /**
//    For string messages
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @GetMapping(value = "/{firstName}", produces = { "application/json"})
    public ResponseEntity getPersonInfo(@PathVariable("firstName") String firstName) {

        sendStringMessage(firstName);

        return new ResponseEntity("\"sendStatus\": \"OK\"", HttpStatus.OK);
    }

    //To send string messages
    private void sendStringMessage(String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(KafkaTopicConfig.topic1, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent string message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send string message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }
*/

}
