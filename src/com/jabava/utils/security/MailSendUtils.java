package com.jabava.utils.security;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;

import com.jabava.pojo.system.MailProperties;


public class MailSendUtils {
	private final static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	
	public static boolean sendMail(MailProperties mailProperties){
		 Properties properties = new Properties();
	     properties.put("mail.smtp.auth", "true");
	     properties.put("mail.smtp.host", mailProperties.getHost());
	     properties.put("mail.smtp.port", mailProperties.getPort());
	     if(mailProperties.isUseSSL()){
	    	 properties.put("mail.smtp.ssl.enable", "true");
		     properties.put("mail.smtp.socketFactory.class", SSL_FACTORY);  //使用JSSE的SSL socketfactory来取代默认的socketfactory
		     properties.put("mail.smtp.socketFactory.fallback", "false");  // 只处理SSL的连接,对于非SSL的连接不做处理
		     properties.put("mail.smtp.socketFactory.port", mailProperties.getPort());
	     }
	     
	     Session session = Session.getInstance(properties);
	     session.setDebug(true);      
	     MimeMessage message = new MimeMessage(session);
	     try {
				// 发件人
				Address address = new InternetAddress(mailProperties.getUserName());
				message.setFrom(address);

				// 收件人
				for (String recipient : mailProperties.getSendTo()) {
					System.out.println("收件人：" + recipient);
					Address toAddress = new InternetAddress(recipient);
					message.setRecipient(MimeMessage.RecipientType.TO, toAddress); // 设置收件人,并设置其接收类型为TO
					/**
					 * TO：代表有健的主要接收者。 CC：代表有健的抄送接收者。 BCC：代表邮件的暗送接收者。
					 * */
				}
				// 主题
				message.setSubject(MimeUtility.encodeWord(mailProperties.getSubject()));
				// 时间
				message.setSentDate(new Date());
				Multipart multipart = new MimeMultipart();
				// 添加文本
				BodyPart text = new MimeBodyPart();
				text.setText(mailProperties.getContent());
				multipart.addBodyPart(text);

				message.setContent(multipart);
				message.saveChanges();

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			try {
				Transport transport=null;
				try {
					transport = session.getTransport(mailProperties.getType());
				} catch (Exception e) {
					e.printStackTrace();
				}
				transport.connect(mailProperties.getHost(), mailProperties.getUserName(), mailProperties.getPassword());
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
	}

	/**
	  * 进行base64加密，防止中文乱码
	  * 
	  */
	public static String changeEncode(String str) {
		if(StringUtils.isBlank(str)){
			return null;
		}
		try {
			str = MimeUtility.encodeText(new String(str.getBytes(), "UTF-8"),"UTF-8", "B"); // "B"代表Base64
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
}
