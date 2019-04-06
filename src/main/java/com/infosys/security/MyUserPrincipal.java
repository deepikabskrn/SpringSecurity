package com.infosys.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.infosys.entity.User;

public class MyUserPrincipal implements UserDetails{
	private User user;
	public MyUserPrincipal(User user) {
	this.user = user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		//authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return authList;
		//return AuthorityUtils.createAuthorityList(user.getRole());
		//return null; 
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		if (user!=null)
			return user.getPassword();
		return null;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		if (user!=null)
			return user.getName();
		return null;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
