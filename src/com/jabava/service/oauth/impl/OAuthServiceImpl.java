package com.jabava.service.oauth.impl;
 
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.springframework.stereotype.Service;

import com.jabava.dao.oauth.OauthClientMapper;
import com.jabava.dao.oauth.OauthTokenMapper;
import com.jabava.pojo.oauth.OauthClient;
import com.jabava.pojo.oauth.OauthToken;
import com.jabava.service.oauth.OAuthService;
 
@Service("oAuthService")
public class OAuthServiceImpl implements OAuthService {
 
	@Resource
	private OauthClientMapper oauthClientMapper;
	   
	@Resource
	private OauthTokenMapper oauthTokenMapper;
 
	public OauthToken validateAccessToken(String accessToken) {
		OauthToken oauthToken = oauthTokenMapper.findOauthTokenByAccessToken(accessToken);
		if (oauthToken == null) {
			return null;
		}
 
		int expireIn = oauthToken.getExpireIn();
		if (expireIn == 0) {
			return oauthToken;
		}
 
		Date createDate = oauthToken.getCreateDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(createDate);
		calendar.add(14, expireIn);
		Date date = calendar.getTime();
		if (date.after(new Date())) {
			return oauthToken;
		}
		return null;
	}
 
	public String getUsernameByAccessToken(String accessToken) {
		return null;
	}
 
	public long getExpireIn() {
		return 0L;
	}
 
	public OauthToken validRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OauthToken token = null;
		try {
			OAuthAccessResourceRequest oauthRequest = 
					new OAuthAccessResourceRequest(request, new ParameterStyle[] { ParameterStyle.HEADER });
 
			String accessToken = oauthRequest.getAccessToken();
 
			token = validateAccessToken(accessToken);
		} catch (OAuthProblemException|OAuthSystemException ex) {
			throw new RuntimeException(ex);
		}
		return token;
	}
 
	public OauthToken createOAuthIssuer(OauthClient oauthClient) {
		Long oauthClientId = oauthClient.getId();
 
		OauthToken oauthToken = null;
		try {
			OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
			String accessToken = oauthIssuerImpl.accessToken();
			String refreshToken = oauthIssuerImpl.refreshToken();
			//oauthToken = new OauthToken(oauthClientId, accessToken, refreshToken);
			oauthToken = new OauthToken();
			oauthToken.setOauthClientId(oauthClientId);
			oauthToken.setAccessToken(accessToken);
			oauthToken.setRefreshToken(refreshToken);
			oauthToken.setExpireIn(oauthClient.getExpireIn());
			if(!oauthClient.getMultipleToken().booleanValue()){
				//如果不允许同时存在多个有效Token，则必须先删除
				//oauthTokenMapper.updateToken(oauthToken, !oauthClient.getMultipleToken().booleanValue());
				oauthTokenMapper.deleteLogicByClientId(oauthClientId, new Timestamp(new Date().getTime()));
			}
       
			oauthTokenMapper.insertSelective(oauthToken);
		} catch (OAuthSystemException e) {
			throw new RuntimeException(e);
		}
		return oauthToken;
	}
 
	public OauthClient findValidAuthClient(OAuthTokenRequest oauthRequest) {
		String clientId = oauthRequest.getClientId();
		String clientSecret = oauthRequest.getClientSecret();
 
		OauthClient oauthClient = oauthClientMapper.selectByClientId(clientId);
		if (oauthClient == null) {
			return null;
		}
		boolean ok = StringUtils.equals(clientSecret, oauthClient.getClientSecret());
		return ok ? oauthClient : null;
	}
 
	public OauthClient loadClient(Long id) {
		return oauthClientMapper.selectByPrimaryKey(id);
	}
}
