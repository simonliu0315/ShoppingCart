package com.waterproof.bjb.shopping.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
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

import com.waterproof.bjb.shopping.dto.ProductInCartDto;
import com.waterproof.bjb.shopping.dto.ProductTempOrder;

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
			String from = environment.getProperty("spring.mail.from");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from, "液態橡膠網站管理者"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(getAdminMail()));
			message.setSubject(subject);
			//message.setText(text);
			message.setContent(text, "text/html; charset=utf-8");
			Transport transport = session.getTransport("smtp");
			log.info("host:{}, port: {}, username: {}, password: {}, to: {}, subject: {}, text: {}", host, port, username, password, to, subject, text);
			transport.connect(host, port, username, password);

			Transport.send(message);

			log.info("寄送email結束.");

		} catch (MessagingException e) {
			log.info("寄送email錯誤.", e);
			//throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			log.info("寄送email錯誤.", e);
			//throw new RuntimeException(e);
		}
	}
	
	
	public static StringBuilder getCustomerOrderMailContent(String username, String orderNumber, ProductInCartDto dto) {
		StringBuilder mailMessage = new StringBuilder();
        mailMessage.append("");
        //空白行
        mailMessage.append("<table width=\"100%\" bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"m_-8436137274855084469backgroundTable\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td>");
        mailMessage.append("<table width=\"600\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td align=\"center\" height=\"20\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        
        //訂購資訊文字描述
        mailMessage.append("<table width=\"100%\" bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"m_-8436137274855084469backgroundTable\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td>");
        mailMessage.append("<table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\">");
        mailMessage.append("<table bgcolor=\"#ffffff\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">");
        mailMessage.append(" <tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td height=\"10\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td>");
        mailMessage.append("<table width=\"560\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td style=\"font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;text-align:left;line-height:18px\">");
        mailMessage.append("嗨 "+username+",");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\" height=\"10\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td style=\"font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;text-align:left;line-height:18px\">");
        mailMessage.append("感謝您訂購相關商品訂單編號"+ orderNumber + "<wbr>請完成相關付款作業。<wbr>若您付款完成且收到商品無誤，請確認商品，<wbr>如有問題，請洽客服。");
        mailMessage.append(" 提醒您，若你在七天內沒有任何回應，<wbr>系統將自動進行訂單完成流程。");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\" height=\"10\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td height=\"10\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\" height=\"1\" bgcolor=\"#ffffff\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        
        //訂單明細中文
        mailMessage.append("<table width=\"100%\" bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"m_-8436137274855084469backgroundTable\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td>");
        mailMessage.append("<table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\">");
        mailMessage.append("<table bgcolor=\"#ffffff\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td height=\"0\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td>");
        mailMessage.append("<table width=\"560\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td style=\"font-family:Helvetica,arial,sans-serif;font-size:13px;color:#889098;text-align:center;line-height:16px\"><table width=\"px\" align=\"left\">");
        mailMessage.append("<tbody><tr><td colspan=\"2\" style=\"text-align:left;font-family:Helvetica,arial,sans-serif;color:#1f1f1f;font-size:16px;font-weight:bold;height:10px\"> </td></tr>");
        mailMessage.append("<tr><td colspan=\"2\" style=\"text-align:left;font-family:Helvetica,arial,sans-serif;color:#1f1f1f;font-size:13px;font-weight:bold\">訂單明細</td></tr>");
        mailMessage.append("</tbody></table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\" height=\"\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td height=\"10\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        
        //訂單編號
        mailMessage.append("<table width=\"100%\" bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"m_-8436137274855084469backgroundTable\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td>");
        mailMessage.append("<table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\">");
        mailMessage.append("<table bgcolor=\"#ffffff\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td height=\"0\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td>");
        mailMessage.append("<table width=\"560\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td style=\"font-family:Helvetica,arial,sans-serif;font-size:13px;color:#889098;text-align:center;line-height:16px\"><table width=\"px\" align=\"left\">");
        mailMessage.append("<tbody><tr><td colspan=\"2\" style=\"text-align:left;font-family:Helvetica,arial,sans-serif;color:#1f1f1f;font-size:16px;font-weight:bold;height:px\"> </td></tr>");
        mailMessage.append("<tr><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;white-space:nowrap;vertical-align:top\">訂單編號: </td><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;vertical-align:top\">"+orderNumber+"</td></tr>");
        mailMessage.append("<tr><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;white-space:nowrap;vertical-align:top\">訂單日期: </td><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;white-space:nowrap;vertical-align:top\">"+ShoppingDateUtil.date2String(new Date(), "yyyy-MM-dd hh:mm:ss")+"</td></tr>");
        //mailMessage.append("<tr><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;white-space:nowrap;vertical-align:top\">賣家名稱: </td><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;white-space:nowrap;vertical-align:top\">linjeffery890628</td></tr>");
        mailMessage.append("</tbody></table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\" height=\"10\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td height=\"10\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        
        //訂單明細清單
        for(ProductTempOrder productTempOrder : dto.getProductsTempOrder()) {
        	mailMessage.append("<table width=\"100%\" bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"m_-8436137274855084469backgroundTable\">");
            mailMessage.append("<tbody>");
            mailMessage.append("<tr>");
            mailMessage.append("<td>");
            mailMessage.append("<table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">");
            mailMessage.append("<tbody>");
            mailMessage.append("<tr>");
            mailMessage.append("<td width=\"100%\">");
            mailMessage.append("<table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"left\">");
            mailMessage.append("<tbody>");
            mailMessage.append("<tr>");
            mailMessage.append("<td>");
            mailMessage.append("<table width=\"180\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
            mailMessage.append("<tbody>");
            mailMessage.append("<tr>");
            mailMessage.append("<td width=\"180\" height=\"140\" align=\"left\" style=\"padding-left:20px\">");
            mailMessage.append("<img src=\"http://www.liquidrubber-diy.com.tw" + productTempOrder.getImg().replaceAll(" ", "%20")+"\" alt=\"\" border=\"0\" width=\"140\" height=\"140\" style=\"display:block;border:none;outline:none;text-decoration:none\">");
            mailMessage.append("</td>");
            mailMessage.append("</tr>");
            mailMessage.append("</tbody>");
            mailMessage.append("</table>");
            mailMessage.append("<table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
            mailMessage.append("<tbody>");
            mailMessage.append("<tr>");
            mailMessage.append("<td width=\"100%\" height=\"15\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
            mailMessage.append("</tr>");
            mailMessage.append("</tbody>");
            mailMessage.append("</table>");
            mailMessage.append("<table width=\"280\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
            mailMessage.append("<tbody>");
            mailMessage.append("<tr>");
            mailMessage.append("<td>");
            mailMessage.append("<table width=\"100%\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
            mailMessage.append("<tbody>");
            mailMessage.append("<tr>");
            mailMessage.append("<td style=\"font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;text-align:left;line-height:px;padding-left:10px\"> "+productTempOrder.getProductName()+"");
            mailMessage.append("</td>");
            mailMessage.append("</tr>");
            mailMessage.append("<tr>");
            mailMessage.append("<td width=\"\" height=\"15\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
            mailMessage.append("</tr>");
            mailMessage.append("<tr>");
            mailMessage.append("<td style=\"font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;text-align:left;line-height:px\">");
            mailMessage.append("<table width=\"100%\" align=\"left\">");
            mailMessage.append("<tbody><tr>");
            mailMessage.append("<td style=\"text-align:left;padding-left:10px;font-family:Helvetica,arial,sans-serif;color:#747474;font-size:13px;white-space:nowrap;width:80px\">Variation: </td>");
            mailMessage.append("<td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;color:#747474;font-size:13px\">"+productTempOrder.getColorName()+"</td>");
            mailMessage.append("</tr>");
            mailMessage.append("<tr>");
            mailMessage.append("<td style=\"text-align:left;padding-left:10px;font-family:Helvetica,arial,sans-serif;color:#747474;font-size:13px;white-space:nowrap;width:80px\">Quantity: </td>");
            mailMessage.append("<td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;color:#747474;font-size:13px\">"+productTempOrder.getQuantity()+"</td>");
            mailMessage.append("</tr>");
            mailMessage.append("<tr>");
            mailMessage.append("<td style=\"text-align:left;padding-left:10px;font-family:Helvetica,arial,sans-serif;color:#747474;font-size:13px;white-space:nowrap;width:80px\">Price: </td>");
            mailMessage.append("<td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;color:#747474;font-size:13px\">NT$ "+productTempOrder.getPrice()+"</td>");
            mailMessage.append("</tr>");
            mailMessage.append("</tbody></table>");
            mailMessage.append("</td>");
            mailMessage.append("</tr>");
            mailMessage.append("<tr>");
            mailMessage.append("<td width=\"100%\" height=\"15\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
            mailMessage.append("</tr>");
            mailMessage.append("</tbody>");
            mailMessage.append("</table>");
            mailMessage.append("</td>");
            mailMessage.append("</tr>");
            mailMessage.append("</tbody>");
            mailMessage.append("</table>");
            mailMessage.append("</td>");
            mailMessage.append("</tr>");
            mailMessage.append("</tbody>");
            mailMessage.append("</table>");
            mailMessage.append("</td>");
            mailMessage.append("</tr>");
            mailMessage.append("</tbody>");
            mailMessage.append("</table>");
            mailMessage.append("</td>");
            mailMessage.append("</tr>");
            mailMessage.append("</tbody>");
            mailMessage.append("</table>");
        }
        
        
        //總金額
        mailMessage.append("<table width=\"100%\" bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"m_-8436137274855084469backgroundTable\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td>");
        mailMessage.append("<table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\">");
        mailMessage.append("<table bgcolor=\"#ffffff\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td height=\"0\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td>");
        mailMessage.append("<table width=\"560\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
        mailMessage.append("<tbody>");
        mailMessage.append("<tr>");
        mailMessage.append("<td style=\"font-family:Helvetica,arial,sans-serif;font-size:13px;color:#889098;text-align:center;line-height:16px\"><table width=\"\" align=\"left\">");
        mailMessage.append("<tbody><tr><td colspan=\"2\" style=\"text-align:left;font-family:Helvetica,arial,sans-serif;color:#1f1f1f;font-size:16px;font-weight:bold;height:10px\"> </td></tr>");
        mailMessage.append("<tr><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;white-space:nowrap;vertical-align:top\">商品總額: </td><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;vertical-align:top\">NT$ "+dto.getSubTotalAmt()+"</td></tr>");
        mailMessage.append("<tr><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;white-space:nowrap;vertical-align:top\">物流費用: </td><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;vertical-align:top\">NT$ +"+dto.getShipment()+"</td></tr>");
        mailMessage.append("<tr><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;white-space:nowrap;vertical-align:top\">總金額: </td><td style=\"text-align:left;font-family:Helvetica,arial,sans-serif;font-size:13px;color:#747474;white-space:nowrap;vertical-align:top\">NT$ "+dto.getTotalAmt()+"</td></tr>");
        mailMessage.append("</tbody></table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td width=\"100%\" height=\"10\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("<tr>");
        mailMessage.append("<td height=\"10\" style=\"font-size:1px;line-height:1px\">&nbsp;</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        mailMessage.append("</td>");
        mailMessage.append("</tr>");
        mailMessage.append("</tbody>");
        mailMessage.append("</table>");
        
        return mailMessage;
	}
	
	public static StringBuilder getChangeOrderStatusMailContent(String orderNumber, String fromStatus, String toStatus) {
		StringBuilder mailMessage = new StringBuilder();
        mailMessage.append("訂單編號:" +orderNumber + ", 狀態由" + fromStatus + "改為" + toStatus + "<br/>");
        mailMessage.append("<br/><br/>系統管理者敬啟");
        return mailMessage;
	}
	
	public String getAdminMail() {
		return environment.getProperty("spring.mail.from");
	}
}
