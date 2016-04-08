package com.jabava.controller.oauth;

import com.jabava.pojo.oauth.OauthClient;
import com.jabava.pojo.oauth.OauthToken;
import com.jabava.service.oauth.OAuthService;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorizeController {

	@Autowired
	private OAuthService oAuthService;
	
	@RequestMapping("/api/authorize")
	public void authorize(HttpServletRequest request, HttpServletResponse response) 
			throws URISyntaxException, OAuthSystemException, IOException {
	    OAuthResponse r;
	    try {
	    	OAuthTokenRequest oauthRequest = new OAuthTokenRequest(request);
	    	OauthClient oauthClient = this.oAuthService.findValidAuthClient(oauthRequest);
	    	if (oauthClient == null) {
	    		throw OAuthProblemException.error("clientId and clientSecret are not match");
	    	}
	    	OauthToken oauthToken = this.oAuthService.createOAuthIssuer(oauthClient);
	
	    	String accessToken = oauthToken.getAccessToken();
	    	String refreshToken = oauthToken.getRefreshToken();
	
	    	r = OAuthASResponse.tokenResponse(200).setAccessToken(accessToken)
	    			.setExpiresIn(oauthClient.getExpireIn().toString()).setRefreshToken(refreshToken)
	    			.buildJSONMessage();
	    } catch (OAuthProblemException ex) {
	    	r = OAuthResponse.errorResponse(401).error(ex).buildJSONMessage();
	    }

	    response.setStatus(r.getResponseStatus());
	    PrintWriter pw = response.getWriter();
	    pw.print(r.getBody());
	    pw.flush();
	    pw.close();
  	}
}
