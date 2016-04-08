package com.ezhiyang.core.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.log4j.Logger;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.cas.userdetails.AbstractCasAssertionUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import com.ezhiyang.core.model.MyUser;
import com.jabava.utils.Config;
import com.jabava.utils.JabavaPropertyCofigurer;
import com.service.provider.CenterUserService;
import com.service.provider.entity.CenterSysUser;
import com.service.provider.entity.ReturnS;

public class JabavaCasUserDetailsService extends AbstractCasAssertionUserDetailsService {
	private Logger log = Logger.getLogger(JabavaCasUserDetailsService.class);

	private CenterUserService centerUserService;

	private UserDetailsService userDetailsService = null;

	private final String[] attributes;

	private boolean convertToUpperCase = false;

	public JabavaCasUserDetailsService() {
		attributes = new String[] {};
	}

	public JabavaCasUserDetailsService(final String[] attributes) {
		Assert.notNull(attributes, "attributes cannot be null.");
		Assert.isTrue(attributes.length > 0, "At least one attribute is required to retrieve roles from.");
		this.attributes = attributes;
	}

	@Override
	protected UserDetails loadUserDetails(final Assertion assertion) {
		log.info("loadUserDetails");
		
		final List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

		for (final String attribute : this.attributes) {
			final Object value = assertion.getPrincipal().getAttributes().get(attribute);

			if (value == null) {
				continue;
			}

			if (value instanceof List) {
				final List<?> list = (List<?>) value;

				for (final Object o : list) {
					grantedAuthorities.add(new SimpleGrantedAuthority(this.convertToUpperCase ? o.toString()
							.toUpperCase() : o.toString()));
				}

			} else {
				grantedAuthorities.add(new SimpleGrantedAuthority(this.convertToUpperCase ? value.toString()
						.toUpperCase() : value.toString()));
			}

		}

		String principalName = assertion.getPrincipal().getName();

		Long passportLoginId = Long.valueOf(principalName);

		//ReturnS returnS = centerUserService.getCenterUser(passportLoginId, CenterUserService.SYSTEM_ID_HRO);
		String systemId = JabavaPropertyCofigurer.getSystemId();
		ReturnS returnS = centerUserService.getCenterUser(passportLoginId, Long.valueOf(systemId));
		if (returnS == null) {
			throw new UsernameNotFoundException(principalName);
		}

		CenterSysUser centerSysUser = (CenterSysUser) returnS.getResult();
		if (centerSysUser == null) {
			throw new DisabledException(principalName);
			//			throw new UsernameNotFoundException(principalName);
		}
		com.service.provider.entity.OrgUser orgUser = centerSysUser.getOrgUser();
		
		MyUser myUser = new MyUser();
		try {
			BeanUtils.copyProperties(myUser, orgUser);

//			//生成自己的User、加载权限并保存到Session
//			HttpServletRequest request = ((ServletRequestAttributes)   
//	                RequestContextHolder.currentRequestAttributes()).getRequest();
//			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
//			UserController uc = wac.getBean(UserController.class);
//			uc.doLogin(orgUser.getIdentity(), orgUser.getOrgId(), request);
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}catch (Exception e) {
			
		}

		return myUser;
	}

	/**
	 * Converts the returned attribute values to uppercase values.
	 * 
	 * @param convertToUpperCase
	 *            true if it should convert, false otherwise.
	 */
	public void setConvertToUpperCase(final boolean convertToUpperCase) {
		this.convertToUpperCase = convertToUpperCase;
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public CenterUserService getCenterUserService() {
		return centerUserService;
	}

	public void setCenterUserService(CenterUserService centerUserService) {
		this.centerUserService = centerUserService;
	}
	static {
		java.util.Date defaultValue = null;
		Converter converter = new DateConverter(defaultValue);
		BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
		beanUtilsBean.getConvertUtils().register(converter, java.util.Date.class);
	}

}
