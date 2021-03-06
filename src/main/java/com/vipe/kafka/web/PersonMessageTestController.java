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

@RestController(value = "PersonMessageTestController")
@RequestMapping(value = "/api/test")
public class PersonMessageTestController {

    @Autowired
    private KafkaTemplate<String, Person> kafkaTemplate2;

    @GetMapping(value = "/{firstName}", produces = { "application/json"})
    public ResponseEntity getPersonInfo(@PathVariable("firstName") String firstName) {

        Person person = new Person("Viraj", "Perera", 35);
        sendMessageWithPayLoad(person);

        return new ResponseEntity(person, HttpStatus.OK);
    }


    //Send messages with payload
    private void sendMessageWithPayLoad(Person person) {

        Message<Person> messageWithPayload = MessageBuilder
                .withPayload(person)
                .setHeader(KafkaHeaders.TOPIC, KafkaTopicConfig.topic2)
                .build();
        ListenableFuture<SendResult<String, Person>> future = kafkaTemplate2.send(messageWithPayload);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Person>>() {

            @Override
            public void onSuccess(SendResult<String, Person> result) {
                System.out.println("Sent message=[" + person.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + person.toString() + "] due to : " + ex.getMessage());
            }
        });
    }

}
