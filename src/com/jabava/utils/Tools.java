package com.jabava.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.jabava.common.exception.DefaultException;
import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.utils.mail.MailVO;

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
	
	/**
	 * 使用模板发送邮件
	 * @param recipients
	 * @param subject
	 * @param params
	 * @param template
	 * @param files
	 * @throws Exception
	 */
	public static void mailSend(String recipients, String subject, Map<String,Object> params,
			String template, List<File> files) throws Exception {
		String body = FileUtil.readHtmlTemplate(template);
		if(StringUtils.isEmpty(body)){
			throw new DefaultException("模板不存在或内容为空");
		}
		
		body = JabavaStringUtils.formatString(body, params);
		mailSend1(recipients, subject, body, files, "");
	}
	
	public static void mailSend1(String recipients, String subject,
			String body, List<File> files, String ccRecipients) throws Exception {
		Transport transport = null;
		try {
//			Properties properties = new Properties();
//			InputStream in = Mail.class.getResourceAsStream("/com/jabava/resources/config.properties");
//			properties.load(in);
//			String mailHost = properties.getProperty("JABAVA_SMTP");
//			Session session = Session.getInstance(properties);
			
			Session session = Session.getInstance(new Properties());
			session.setDebug(false);// 开启后有调试信息
			MimeMessage message = new MimeMessage(session);

			// 发件人
//			InternetAddress from = new InternetAddress(properties.getProperty("JABAVA_MAIL"));
			InternetAddress from = new InternetAddress(JabavaPropertyCofigurer.getProperty("JABAVA_MAIL"));
			message.setFrom(from);

			// 收件人
			InternetAddress to = new InternetAddress(recipients);
			message.setRecipient(Message.RecipientType.TO, to);
			//message.setRecipient(Message.RecipientType.CC, from);

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
//			transport.connect(properties.getProperty("JABAVA_SMTP"), 
//					properties.getProperty("JABAVA_MAIL"),
//					properties.getProperty("JABAVA_MAIL_PWD"));
			transport.connect(JabavaPropertyCofigurer.getProperty("JABAVA_SMTP"),
					JabavaPropertyCofigurer.getProperty("JABAVA_MAIL"),
					JabavaPropertyCofigurer.getProperty("JABAVA_MAIL_PWD"));
			
			// 发送
//			transport.sendMessage(message, message.getAllRecipients());
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			//transport.sendMessage(message, message.getRecipients(Message.RecipientType.CC));
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
	
	public static Map<String, Integer> mailSend(Map<String, MailVO> mailVOMap) throws Exception{
		Transport transport = null;
		Map<String, Integer> result = new HashMap<String, Integer>();
		try {
			//初始化Session
			Session session = Session.getInstance(new Properties());
			session.setDebug(false);// 开启后有调试信息

			transport = session.getTransport("smtp");
			transport.connect(JabavaPropertyCofigurer.getProperty("JABAVA_SMTP"),
					JabavaPropertyCofigurer.getProperty("JABAVA_MAIL"),
					JabavaPropertyCofigurer.getProperty("JABAVA_MAIL_PWD"));
			
			//多线程发送邮件
			ExecutorService executor = Executors.newCachedThreadPool();
			Map<String, Future<Integer>> futureMap = new HashMap<String, Future<Integer>>();
			for(String key : mailVOMap.keySet()){
				MailSendTask task = new MailSendTask(session, transport, mailVOMap.get(key));
				Future<Integer> future = executor.submit(task);
				futureMap.put(key, future);
			}
			executor.shutdown();
			
			//等待结果
			int times = 0;
			while(!executor.isTerminated()){
				times ++;
				if(times > 600){	//1000ms * 600 = 10分钟
					throw new DefaultException("批量发送邮件超时了");
				}
				Thread.sleep(1000);
			}
			
			//处理结果
			for(String key : futureMap.keySet()){
				Future<Integer> future = futureMap.get(key);
				if(future == null || future.get() == null){
					result.put(key, 0);
				}else{
					result.put(key, future.get());
				}
			}
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefaultException("批量发送邮件出错", e);
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

class MailSendTask implements Callable<Integer>{
	private Session session;
	private Transport transport;
	private MailVO mailVO;
	
	public MailSendTask(Session session, Transport transport, MailVO mailVO){
		this.session = session;
		this.transport = transport;
		this.mailVO = mailVO;
	}
	
	@Override
	public Integer call() throws Exception {
		try {
			MimeMessage message = new MimeMessage(session);

			// 发件人
			//InternetAddress from = new InternetAddress(JabavaPropertyCofigurer.getProperty("JABAVA_MAIL"));
			InternetAddress from = new InternetAddress(mailVO.getFrom());
			message.setFrom(from);

			// 收件人
			InternetAddress to = new InternetAddress(mailVO.getTo());
			message.setRecipient(Message.RecipientType.TO, to);

			// 邮件主题
			message.setSubject(mailVO.getSubject());

			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();

			// 添加邮件正文
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setContent(mailVO.getContent(), "text/html;charset=UTF-8");
			multipart.addBodyPart(contentPart);

			// 将multipart对象放到message中
			message.setContent(multipart);
			
			// 保存邮件
			message.saveChanges();
			
			transport.sendMessage(message, message.getAllRecipients());
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
}
