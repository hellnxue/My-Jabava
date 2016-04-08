package com.jabava.service.oauth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;

import com.jabava.pojo.oauth.OauthClient;
import com.jabava.pojo.oauth.OauthToken;

public interface OAuthService {
	public abstract OauthClient findValidAuthClient(OAuthTokenRequest oauthRequest);

	public OauthToken createOAuthIssuer(OauthClient paramOauthClient);

	public String getUsernameByAccessToken(String paramString);

	public long getExpireIn();

	public OauthToken validRequest(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
			throws ServletException, IOException;

	public OauthClient loadClient(Long paramLong);
}