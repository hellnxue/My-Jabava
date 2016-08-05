package com.jabava.utils.mobile;

import java.util.ArrayList;
import java.util.List;

import com.jabava.core.config.JabavaPropertyCofigurer;
import com.wondertek.esmp.esms.empp.EMPPConnectResp;
import com.wondertek.esmp.esms.empp.EMPPData;
import com.wondertek.esmp.esms.empp.EMPPObject;
import com.wondertek.esmp.esms.empp.EMPPShortMsg;
import com.wondertek.esmp.esms.empp.EMPPSubmitSM;
import com.wondertek.esmp.esms.empp.EmppApi;

/**
 * 短信发送工具类
 * 
 * @author 郑长山
 * 
 */
public class MobileValidHelp {

	@SuppressWarnings("unused")
	private static EMPPConnectResp responseConnect = null;

	private static EmppApi emppApi = null;

	private static String mhost = "211.136.163.68";
	private static String mport = "9981";
	private static String maccountId = "10657109030880";
	private static String mpassword = "Admin9095";
	private static String mserviceId = "1";
	private static MobileRecvListener listener;

	static {
		mhost = JabavaPropertyCofigurer.getProperty("mobile.server.host");
		mport = JabavaPropertyCofigurer.getProperty("mobile.server.port");
		maccountId = JabavaPropertyCofigurer.getProperty("mobile.server.account");
		mpassword = JabavaPropertyCofigurer.getProperty("mobile.server.password");
		mserviceId = JabavaPropertyCofigurer.getProperty("mobile.server.service");
		
		emppApi = new EmppApi();
		listener = new MobileRecvListener(emppApi);
		try {
			// 建立同服务器的连接
			String host = mhost;
			int port = Integer.valueOf(mport);
			String accountId = maccountId;
			String password = mpassword;
			// String serviceId = mserviceId;
			EMPPConnectResp response = emppApi.connect(host, port, accountId,
					password, listener);
			System.out.println(response);
			if (response == null) {
				System.out.println("连接超时失败");
			}
			if (!emppApi.isConnected()) {
				System.out.println("连接失败:响应包状态位=" + response.getStatus());
			}
			if (response != null && emppApi.isConnected()) {
				responseConnect = response;
			}
		} catch (Exception e) {
			System.out.println("发生异常，导致连接失败");
			e.printStackTrace();
		}
	}

	/**
	 * 发送手机短信
	 * 
	 * @param mobile
	 *            手机号码
	 * @param content
	 *            发送内容
	 * @return
	 */
	public static Boolean sendMsg(String mobile, String content) {
		try {
			emppApi.sendActiveTestAsync();
		} catch (Exception e1) {
			try {
				emppApi.reConnect(listener);
			} catch (Exception e) {
				e.printStackTrace();
			}
			e1.printStackTrace();
		}
		if (emppApi.isSubmitable()) {
			System.out.println("ready to send");
			// 详细设置短信的各个属性,不支持长短信
			EMPPSubmitSM msg = (EMPPSubmitSM) EMPPObject
					.createEMPP(EMPPData.EMPP_SUBMIT);
			List<String> dstId = new ArrayList<String>();
			dstId.add(mobile);
			msg.setDstTermId(dstId);
			msg.setSrcTermId(maccountId);
			msg.setServiceId(mserviceId);

			EMPPShortMsg msgContent = new EMPPShortMsg(
					EMPPShortMsg.EMPP_MSG_CONTENT_MAXLEN);
			try {
				msgContent.setMessage(content.getBytes("GBK"));
				msg.setShortMessage(msgContent);
				msg.assignSequenceNumber();
				emppApi.submitMsgAsync(msg);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
}