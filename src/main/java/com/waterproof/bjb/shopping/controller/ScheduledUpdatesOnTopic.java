package com.waterproof.bjb.shopping.controller;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Component
public class ScheduledUpdatesOnTopic {

    @Autowired
    private SimpMessagingTemplate template;

    //@Scheduled(fixedDelay=2000)
    public void publishUpdates(){
    	HelloMessage message = new HelloMessage();
    	message.setName(new Date().toString());
    	JSONObject inner=new JSONObject();
    	inner.put("name", message.getName());
    	Greeting g = new Greeting(new Date().toString());
    	template.convertAndSend("/topic/greetings", g );
    	log.info("text.....{}", inner.toString());
        //template.convertAndSend("/topic/greetings", text);
    }
}