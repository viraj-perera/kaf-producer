package com.vipe.kafka.pub;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppNotificationPublisher {

    public List<Notification> getNotifications(){
        List<Notification> notifications = new ArrayList<>();

        for (int i=3456; i < 3460; i++){
            notifications.add(new Notification((long)i, "Test message. id="+i , Notification.Type.ALERT));
        }
        return notifications;
    }
}
