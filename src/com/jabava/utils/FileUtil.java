package com.jabava.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.utils.security.MD5;

public class FileUtil {

	public static final Log log = LogFactory.getLog(FileUtil.class);

	/**
	 * <pre>
	 * 根据原始文件名获取其扩展名
	 * </pre>
	 *
	 * @param originalFilename
	 * @return
	 */
	public static String getSuffixName(String originalFilename) {
		String suffix = null;
		if (0 <= originalFilename.lastIndexOf('.') && originalFilename.lastIndexOf('.') < originalFilename.length() - 1) {
			suffix = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
		}
		return suffix;
	}

	/**
	 * 文件上传到服务器
	 * 
	 * @param uploadFile上传的文件
	 * @param serverFilePath
	 *            服务器上保存路径(包括文件名)
	 * @throws Exception
	 */
	public static void uploadFile(File uploadFile, String serverFilePath) throws Exception {
		// 以服务器的文件保存地址和原文件名建立上传文件输出流
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			fos = new FileOutputStream(serverFilePath);
			fis = new FileInputStream(uploadFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			// 将file读进数组
			while ((len = fis.read(buffer)) > 0) {
				// 将读出的结果写进serverFilePath(从零开始)
				fos.write(buffer, 0, len);
			}
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} finally {
				if (fos != null) {
					fos.close();
				}
			}
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void downloadFile(File file, boolean deleteAfterDownload, HttpServletResponse httpServletResponse, String originalName) throws IOException {
		InputStream in = new FileInputStream(file);
		String suffix = getSuffixName(originalName);
		if (StringUtils.isEmpty(originalName)) {
			httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
		} else {
			originalName = java.net.URLEncoder.encode(originalName, "UTF-8").replace("+", "%20");
			if ("zip".equalsIgnoreCase(suffix)) {
				httpServletResponse.addHeader("Content-Disposition", "attachment;filename=" + originalName);
				httpServletResponse.setContentType("application/zip;charset=GBK");
			} else {
				httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + originalName);
			}
		}
		OutputStream out = httpServletResponse.getOutputStream();
		streamOperate(in, out);
		if (deleteAfterDownload) {
			file.delete();
		}
		file = null;
	}

	public static void streamOperate(InputStream in, OutputStream out) {
		int len = 0;
		byte[] buffer = new byte[1024];
		try {
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			out.flush();
		} catch (Exception ioe) {
			log.error("文件读写出错", ioe);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					log.error("输出文件流关闭出错", e);
				}
				out = null;
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.error("输入文件流关闭出错", e);
				}
				in = null;
			}
		}
	}
	
	/**
	 * 读取Html模板(路径为com/jabava/resources/html)
	 * @return
	 */
	public static String readHtmlTemplate(String fileName){
		return readFileContent("com/jabava/resources/html/" + fileName);
	}

	/**
	 * 
	 * <pre>
	 * 获得文件内容
	 * </pre>
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 */
	public static String readFileContent(String filePath) {
		URL url = FileUtil.class.getClassLoader().getResource(filePath);
		try {
			File file = new File(url.toURI());
			BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			
			StringBuilder sb = new StringBuilder();
			String content = "";
			while (content != null) {
				content = bf.readLine();
				//if (StringUtils.isEmpty(content)) {
				if(content == null){
					break;
				}
				sb.append(content.trim());
			}
			bf.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * <pre>
	 * 新建文件
	 * </pre>
	 * 
	 * @param filePathAndName
	 *            文件路径及名称
	 */
	public static void newFile(String filePathAndName) throws IOException {

		String filePath = filePathAndName;
		File myFilePath = new File(filePath);
		if (!myFilePath.exists()) {
			myFilePath.createNewFile();
		}

	}

	/** 
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param   sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */
	public static boolean deleteDirectory(String sPath) {
		//如果sPath不以文件分隔符结尾，自动添加文件分隔符  
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		//如果dir对应的文件不存在，或者不是一个目录，则退出  
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		//删除文件夹下的所有文件(包括子目录)  
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			//删除子文件  
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} //删除子目录  
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		//删除当前目录  
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除  
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * <pre>
	 * 下载Excel 文件
	 * </pre>
	 *
	 * @param filePath
	 * @param response
	 * @param fileName
	 * @param fileType
	 * @return
	 * @throws Exception
	 */
	public static boolean downLoadFile(String filePath, HttpServletResponse response, String fileName, String fileType) throws Exception {
		File file = new File(filePath); //根据文件路径获得File文件
		//设置下载的文件类型
		if ("pdf".equals(fileType)) {
			response.setContentType("application/pdf;charset=GBK");
		} else if ("xlsx".equals(fileType)) {
			response.setContentType("application/msexcel;charset=GBK");
		} else if ("doc".equals(fileType)) {
			response.setContentType("application/msword;charset=GBK");
		}
		//文件名
		//fileName = java.net.URLEncoder.encode(fileName, "UTF-8").replace("+", "%20");
		fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
		
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName));
		response.setContentLength((int) file.length());
		byte[] buffer = new byte[4096];// 缓冲区
		BufferedOutputStream output = null;
		BufferedInputStream input = null;
		try {
			output = new BufferedOutputStream(response.getOutputStream());
			input = new BufferedInputStream(new FileInputStream(file));
			int n = -1;
			//遍历，开始下载
			while ((n = input.read(buffer, 0, 4096)) > -1) {
				output.write(buffer, 0, n);
			}
			output.flush(); //不可少
			response.flushBuffer();//不可少
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭流，不可少
			if (input != null)
				input.close();
			if (output != null)
				output.close();
		}
		return true;
	}
	
	/**
	 * 对(prefix + time + originalName)进行MD5加密
	 * @param prefix
	 * @param originalName
	 * @return
	 */
	public static String generateFileName(String prefix, String originalName){
		return generateFileName(prefix, originalName, false);
	}
	
	public static String generateFileName(String prefix, String originalName, boolean reserveExtName){
		String newName = MD5.getMD5Code(prefix + System.currentTimeMillis() + originalName);
		if(reserveExtName){
			if(prefix.lastIndexOf(".") > -1){
				newName += prefix.substring(prefix.lastIndexOf("."));
			}
		}
		
		return newName;
	}
	
	/**
	 * UPLOAD_PATH+relativeRoot+fileClass+年+月+日
	 * @param relativeRoot
	 * @param fileClass
	 * @return
	 */
	public static String confirmFullPath(String relativeRoot, String fileClass) throws Exception{
		//绝对目录(UPLOAD_PATH+relativeRoot+fileClass+年+月+日)
		String now = JabavaDateUtils.formatDate("yyyyMMdd");
		String fullPath = new StringBuffer(JabavaPropertyCofigurer.getProperty("UPLOAD_PATH"))
				.append(File.separator).append(relativeRoot)
				.append(File.separator).append(fileClass)
				.append(File.separator).append(JabavaDateUtils.formatDate(now.substring(0, 4)))
				.append(File.separator).append(JabavaDateUtils.formatDate(now.substring(4, 6)))
				.append(File.separator).append(JabavaDateUtils.formatDate(now.substring(6, 8))).toString();
		File file  = new File(fullPath);
		if (!file.exists()) {	//不存在则创建该目录
			file.mkdirs();
		}
		
		return fullPath;
	}
	
	public static String getExtension(String fileName) {
		String extension = fileName.substring(fileName.lastIndexOf("."));
		return extension;
	}
	
	public static void main(String[] args){
		String content1 = readFileContent("com/jabava/resources/html/tpl_reset_password.html");
		String content2 = readHtmlTemplate("tpl_reset_password.html");
		System.out.println(content1);
		System.out.println(content2);
	}
}