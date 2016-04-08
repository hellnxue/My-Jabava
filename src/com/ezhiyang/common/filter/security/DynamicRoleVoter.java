package com.ezhiyang.common.filter.security;

import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;


public class DynamicRoleVoter  implements AccessDecisionVoter<Object> {

	@Override
	public boolean supports(ConfigAttribute arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> arg2) {
		    int result = ACCESS_ABSTAIN; 
	        if (!(object instanceof FilterInvocation)){  
	            return result;  
	        }
	        FilterInvocation invo = (FilterInvocation) object;  
	        String url = invo.getRequestUrl();//当前请求的URL 
	        if(authentication!=null && !authentication.getName().equals("anonymousUser")){
//	        	if(url.equals("/")){
//	        		return 0;
//	        	}
	        	if(url.startsWith("/actionPage")){
	        		return 0;
	        	}
	        	return 1;
	        }
			return 0;
	        //获得当前用户的可访问资源，自定义的查询方法，之后和当前请求资源进行匹配，成功则放行，否则拦截      
//	        authorities = loadUserAuthorities(userService.findById(userId));  
//	        Map<String, Set<String>> urlAuths = authService.getUrlAuthorities(); 
//	        Set<String> keySet = urlAuths.keySet();  
//	        for (String key : keySet) {  
//	            boolean matched = pathMatcher.match(key, url);  
//	            if (!matched)  
//	                continue;  
//	            Set<String> mappedAuths = urlAuths.get(key);  
//	            if (contain(authorities, mappedAuths)) {  
//	                result = ACCESS_GRANTED;  
//	                break;  
//	            }  
//	        }  
	}

	
}
