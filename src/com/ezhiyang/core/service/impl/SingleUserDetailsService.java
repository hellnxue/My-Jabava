package com.ezhiyang.core.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jabava.dao.manage.EhrUserMapper;
import com.jabava.pojo.manage.EhrUser;

public class SingleUserDetailsService implements UserDetailsService{  
    @Resource  
    private EhrUserMapper userMapper;  
  
    //登录验证  
    public UserDetails loadUserByUsername(String name)  
            throws UsernameNotFoundException {  
        System.out.println("show login name："+name+" ");  
        EhrUser ehrUser = null;
		try {
			ehrUser = userMapper.searchUserByUserCode(name);
		} catch (Exception e) {
			e.printStackTrace();
		}  
		if(ehrUser == null){
			return null;
		}
		
        Set<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(ehrUser);  
        
        boolean enables = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        //封装成spring security的user  
        User userdetail = new User(ehrUser.getUserCode(), ehrUser.getPassword(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);  
        return userdetail;  
    }
    
    //查找用户权限  
    public Set<GrantedAuthority> obtionGrantedAuthorities(EhrUser ehrUser){  
        Set<GrantedAuthority> authSet=new HashSet<GrantedAuthority>();  
        authSet.add(new SimpleGrantedAuthority("ROLE_LOGIN"));
        return authSet;  
    }  

}
