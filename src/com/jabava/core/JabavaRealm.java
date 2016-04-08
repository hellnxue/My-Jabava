package com.jabava.core;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.stereotype.Service;

import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.manage.IUserService;
import com.jabava.service.manage.impl.UserServiceImpl;

@Service("jabavaRealm")
public class JabavaRealm extends AuthorizingRealm {

	public JabavaRealm() {
		super();

	}

	@Override
	/* 这里编写授权代码 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		EhrUser user = (EhrUser) principals.fromRealm(getName()).iterator()
				.next();
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			return info;
		} 
		return null;
	}

	@Override
	/* 这里编写认证代码 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		EhrUser user = new EhrUser();
		try {
			UsernamePasswordToken upToken = (UsernamePasswordToken) authcToken;
			user.setUserCode(upToken.getUsername());
			user.setPassword(upToken.getPassword().toString());
			if (user == null) {
				throw new UnknownAccountException("No account found for user");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
	}

	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

}
