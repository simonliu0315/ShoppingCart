package com.waterproof.bjb.shopping.service;

import java.io.File;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hitrust.b2ctoolkit.b2cpay.B2CPayAuth;
import com.hitrust.b2ctoolkit.b2cpay.B2CPayUpdate;
import com.hitrust.b2ctoolkit.util.HiMerchant;
import com.hitrust.b2ctoolkit.util.HiServer3;
import com.hitrust.b2ctoolkit.util.ToolkitException;
import com.waterproof.bjb.shopping.dto.PaymentProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentService {

	private final PaymentProperties properties;

	@Autowired
	public PaymentService(PaymentProperties properties) {
	    this.properties = properties;
	}


	
	public B2CPayAuth creditcardAuth(String orderNo, String orderDesc, String amount) {
		B2CPayAuth auth = new B2CPayAuth();
		auth.setStoreId(properties.getStoreId());
		auth.setDepositFlag(properties.getDepositFlag());
		auth.setQueryFlag(properties.getQueryFlag());
		
		auth.setOrderNo(orderNo);
		auth.setOrderDesc(orderDesc);
		
		auth.setAmount(amount + "00");
		
		auth.setReturnURL(properties.getReturnURL());
		auth.setUpdateURL(properties.getUpdateURL());

		auth.transaction();
		InputStream is = HiMerchant.class.getClassLoader().getResourceAsStream("62253.conf");
		log.info("InputStream 1 is = {}", is);
		is = HiServer3.class.getClassLoader().getResourceAsStream("HiServer.conf");
		log.info("InputStream 2 is = {}", is);
		try {
			log.info("{}", "" + new File("D:/Private/購物車/creditcard/RSA/PUB.DER").isFile());
			log.info("RSAName:{}", HiServer3.getRSAName());
		} catch (ToolkitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return auth;
	}
	
	public B2CPayUpdate creditcardPayUpdate(String key, String mac, String cipher) {
		B2CPayUpdate update= new B2CPayUpdate();
		update.setStoreId(properties.getStoreId());
		update.setKey(key);
		update.setMac(mac);
		update.setCipher(cipher);
		update.transaction();
		return update;
	}
	
}
