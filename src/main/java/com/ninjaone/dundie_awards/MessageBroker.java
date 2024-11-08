package com.ninjaone.dundie_awards;

import com.ninjaone.dundie_awards.model.Activity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class MessageBroker {
    private final Collection<Activity> messages = new ConcurrentLinkedQueue<>();

    public void sendMessage(Activity message) {
        messages.add(message);
    }

    public Collection<Activity> getMessages(){
        return messages;
    }
}
