package com.jabava.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
public class Sftp {
	
	private static final Logger logger = Logger.getLogger(Sftp.class);

	
	//sftp的IP地址
	private static final String sftpIp = JabavaPropertyCofigurer.getProperty("sftp_ip");
	//sftp的IP端口号
	private static final int sftpPort = Integer.valueOf(JabavaPropertyCofigurer.getProperty("sftp_port"));
	//sftp的用户名
	private static final String sftpUser = JabavaPropertyCofigurer.getProperty("sftp_user");
	//sftp的密码
	private static final String sftpPassword = JabavaPropertyCofigurer.getProperty("sftp_password");
	//sftp 的  超时时间
	private static final int timeOut = 50000;
	
	
	 /** 
     * 利用JSch包实现SFTP下载、上传文件(用户名密码方式登陆) 
     * @param ip 主机IP 
     * @param user 主机登陆用户名 
     * @param psw  主机登陆密码 
     * @param port 主机ssh2登陆端口，如果取默认值(默认值22)，传-1 
     *  
     */  
    public static void sshSftp(String sourceFile,String destinationFile)throws Exception {  
        System.out.println("开始用户名密码方式登陆");  
        Session session = null;  
          
        JSch jsch = new JSch();  
       //采用指定的端口连接服务器  端口号 20      
		session = jsch.getSession(sftpUser, sftpIp, sftpPort);
		 
        //如果服务器连接不上，则抛出异常  
        if (session == null) {  
        	logger.error("连接sftp服务器出错 session is null", new Exception("session is null"));    
        	 throw new Exception("session is null");  
        }            
        //设置登陆主机的密码  
        session.setPassword(sftpPassword);//设置密码           
        //设置第一次登陆的时候提示，可选值：(ask | yes | no)  
        session.setConfig("StrictHostKeyChecking", "no");  
        //设置登陆超时时间     
       session.connect(timeOut);        
		sftp(session,sourceFile,destinationFile);	
        System.out.println("sftp成功");  
    }  
      
  
      
    @SuppressWarnings("rawtypes")
	private static void sftp(Session session, String sourceFile,String destinationFile) throws Exception {  
        Channel channel = null;  
        OutputStream outstream = null;
        InputStream instream = null;
        try {  
            //创建sftp通信通道  
            channel = (Channel) session.openChannel("sftp");  
            channel.connect(1000);  
            ChannelSftp sftp = (ChannelSftp) channel;  
          
            String str[] =destinationFile.split("/");
            String dstFile= str[str.length-1];//文件名
            String path= str[str.length-2];//最后一级目录 20
            String path2= str[str.length-3];//倒数第二级目录 8
          //  String path3= str[str.length-4];//根目录 weixin
            int index1 = destinationFile.lastIndexOf("/");
   	        String dst =   destinationFile.substring(0, index1);//目录/home/static/www/weixin/8/20
   	       int index2 = dst.lastIndexOf("/");
   	       String dst2 =   destinationFile.substring(0, index2);//一级目录/home/www/static/weixin/8
   	       int index3 = dst2.lastIndexOf("/");
	       String dst3 =   destinationFile.substring(0, index3);//根目录/home/www/static/weixin
   	     
            Vector v = sftp.ls(dst3);
            boolean isDir=true;
			for(int i=0;i<v.size();i++){	
				LsEntry obj =  (LsEntry) v.get(i);
				SftpATTRS t = obj.getAttrs();			
				 boolean isdir = t.isDir();
				 //判断是否是文件夹,如果是文件夹，判断有没有这个文件夹，没有这个文件夹，创建这个文件夹
				 if(isdir){
					  if(obj.getFilename().equals(path2)){//
						  isDir=false;						  
					  }
					 
				  }
			}
			sftp.cd(dst3); //进入这个目录 
			if(isDir){//没有就创建目录
				sftp.mkdir(path2);
			}
			isDir=true;
		   Vector v2 = sftp.ls(dst2);
			for(int i=0;i<v2.size();i++){
				
					LsEntry obj =  (LsEntry) v2.get(i);
					SftpATTRS t = obj.getAttrs();				
					 boolean isdir = t.isDir();
					 //判断是否是文件夹,如果是文件夹，判断有没有这个文件夹，没有这个文件夹，创建这个文件夹
					 if(isdir){
						  if(obj.getFilename().equals(path)){//创建目录
							  isDir=false;							 
						  }
						 
					  }
				}	
			sftp.cd(dst2);
			if(isDir){//没有就创建目录
				 sftp.mkdir(path);
			}
            sftp.cd(dst);            
           //源文件
           instream = new FileInputStream(new File(sourceFile));  
           //目标文件
           outstream = sftp.put(dstFile); 
            byte b[] = new byte[1024];  
            int n;  
            while ((n = instream.read(b)) != -1) {  
                outstream.write(b, 0, n);  
            }  
              
            outstream.flush();  
           
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
        	if(outstream!=null){
        		 outstream.close();  
        	}
        	if(instream!=null){
        		instream.close();  
        	}          
            session.disconnect();  
            channel.disconnect();  
        }  
    }  

}
