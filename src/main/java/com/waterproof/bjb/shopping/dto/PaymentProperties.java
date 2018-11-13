package com.waterproof.bjb.shopping.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix="payment.creditcard")
@Data
public class PaymentProperties {
	
	
	private String storeId;
	
	private String depositFlag;
	
	private String queryFlag;
	
	private String returnURL;
	
	private String updateURL;

}
