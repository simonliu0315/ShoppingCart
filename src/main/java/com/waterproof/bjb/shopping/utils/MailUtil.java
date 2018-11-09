package com.waterproof.bjb.shopping.utils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MailUtil {

	@Autowired
	private Environment environment;

	public void sendByGmail(String subject, String text, String to) {

		String host = environment.getProperty("spring.mail.host");
		int port = NumberUtils.toInt(environment.getProperty("spring.mail.port"), 587);
		String username = environment.getProperty("spring.mail.username");
		String password = environment.getProperty("spring.mail.password");

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", port);
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("liquidrubbertaiwan@gmail.com", "液態橡膠網站管理者"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			//message.setText(text);
			message.setContent(text, "text/html; charset=utf-8");
			Transport transport = session.getTransport("smtp");
			log.info("host:{}, port: {}, username: {}, password: {}", host, port, username, password);
			transport.connect(host, port, username, password);

			Transport.send(message);

			log.info("寄送email結束.");

		} catch (MessagingException e) {
			log.info("寄送email錯誤.", e);
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			log.info("寄送email錯誤.", e);
			throw new RuntimeException(e);
		}
	}
}
