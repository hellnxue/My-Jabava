package com.jabava.utils;

import java.io.File;
import java.io.InputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Tools {
	
	public static String encryptPassword(String password) throws Exception {
		//return new BCryptPasswordEncoder(9).encode(password);
		return new Md5PasswordEncoder().encodePassword(password,null);
//		byte[] x; 
//		String a;
//		MessageDigest alg = MessageDigest.getInstance("sha-1");
//		alg.reset();
//		x = alg.digest(password.getBytes());
//		a = Hex.toString(x);
//		return a;
	}
	
	public static String strToStr(String str) {
		if (str == null)
			return null;
		else
			return "'" + str.replaceAll("'", "''") + "'";
	}
	
	public static String dateToStr(java.util.Date date) {
		return (date == null ? "null" : ("'"
				+ Tools.formatAll(date, "yyyy-MM-dd") + "'"));
	}
	
	public static String strToLikeStr(String str) {
		return " like '%" + str.replaceAll("'", "''") + "%'";
	}

	/**
	 * 
	 * @param date
	 * @将日期类型返回成指定格式的字符串
	 */
	public static String formatAll(Date date, String format) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String ret = sdf.format(date);
		return ret;
	}

	public static Date addDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date sDate = new java.util.Date(cal.get(Calendar.YEAR) - 1900,
				cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
		return sDate;
	}
	
	public static String getInitialPassword() {
		String initialPassword = "";
		for (int i = 0; i < 6; i++) {
			int type = new Double(Math.random() * 3).intValue() + 1;
			initialPassword += getChar(type);
		}
		return initialPassword;

	}
	
	public static char getChar(int type) {
		if (type == 1) {
			return (char) (new Double(Math.random() * 10).intValue() + 48);
		} else if (type == 2) {
			return (char) (new Double(Math.random() * 26).intValue() + 65);
		} else {
			return (char) (new Double(Math.random() * 26).intValue() + 97);
		}
	}
	
	public static void mailSend(String recipients, String subject, String body,
			List<File> files) throws Exception {
		mailSend1(recipients, subject, body, files, "");
	}
	
	private static MimeMessage message;

	private static Transport transport;
	
	public static void mailSend1(String recipients, String subject,
			String body, List<File> files, String ccRecipients) throws Exception {
		try {
			Properties properties = new Properties();
			InputStream in = Mail.class.getResourceAsStream("/com/jabava/resources/config.properties");
			properties.load(in);

			String mailHost = properties.getProperty("JABAVA_SMTP");

			Session session = Session.getInstance(properties);
			session.setDebug(false);// 开启后有调试信息
			message = new MimeMessage(session);

			// String receiveUser = request.getParameter("receiveUser");
			// String sendHtml = request.getParameter("sendHtml");
			// String receiveUser = request.getParameter("attachment");
			// 发件人
			InternetAddress from = new InternetAddress(
					properties.getProperty("JABAVA_MAIL"));
			message.setFrom(from);

			// 收件人
			InternetAddress to = new InternetAddress(recipients);
			message.setRecipient(Message.RecipientType.TO, to);
			
			message.setRecipient(Message.RecipientType.CC, from);

			// 邮件主题
			message.setSubject(subject);

			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();

			// 添加邮件正文
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setContent(body, "text/html;charset=UTF-8");
			multipart.addBodyPart(contentPart);

			// 将multipart对象放到message中
			message.setContent(multipart);
			// 保存邮件
			message.saveChanges();

			transport = session.getTransport("smtp");
			// smtp验证，就是你用来发邮件的邮箱用户名密码
			transport.connect(mailHost, properties.getProperty("JABAVA_MAIL"),
					properties.getProperty("JABAVA_MAIL_PWD"));
			// 发送
//			transport.sendMessage(message, message.getAllRecipients());
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.CC));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (transport != null) {
				try {
					transport.close();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		String source = "001";
		System.out.println(Tools.encryptPassword(source));
	}
}
