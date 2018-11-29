package com.waterproof.bjb.shopping.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NotifyUtil {

	@Autowired
	private Environment environment;
	
	public String getBankTransferInfo(){
		String bankCode = environment.getProperty("shopping.bankcode");
		String accountNo = environment.getProperty("shopping.accountNo");
		String accountName = environment.getProperty("shopping.accountName");
		StringBuilder sb = new StringBuilder("");
		sb.append("<p class='text-center'>ATM繳款資料如下:</p>");
		sb.append("<p class='text-center'>轉帳銀行:"+ bankCode +"</p>");
		sb.append("<p class='text-center'>ATM轉帳帳號:" + accountNo + "</p>");
		sb.append("<p class='text-center'>戶名:" + accountName + "</p>");
		
		return sb.toString();
	}
	
	public String getBankCode() {
		return environment.getProperty("shopping.bankcode");
	}
	
	public String getAccountCode() {
		return environment.getProperty("shopping.accountNo");	
	}
	
	public String getAccountName() {
		return environment.getProperty("shopping.accountName");
	}
}
