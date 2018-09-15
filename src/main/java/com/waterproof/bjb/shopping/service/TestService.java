package com.waterproof.bjb.shopping.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TestService {

	
	@Autowired
    private TaskExecutor taskExecutor;
	
    @Autowired
    private ApplicationContext applicationContext;
    
    //@Scheduled(fixedRate = 100)
	public void executeAsynchronously() {
		
		MyThread myThread = applicationContext.getBean(MyThread.class);
        taskExecutor.execute(myThread);
	}
//	@Scheduled(fixedRate = 100)
//	@Async
//	public void reportCurrentTime() {
//		executeAsynchronously();
//
//	}

}
