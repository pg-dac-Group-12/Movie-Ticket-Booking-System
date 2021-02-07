package com.app.bookmymovie.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.bookmymovie.pojo.Actor;

@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails {
	
	private Actor actor ;

	public CustomUserDetails(Actor actor) {
		super();
		this.actor = actor;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> roles = new ArrayList<>();
				roles.add(new SimpleGrantedAuthority(actor.getRole().toString()));
				System.out.println("ROle"+roles);
		return roles ;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		//System.out.println(actor.getPassword()+ "PASSWORD");
		return actor.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub

		//System.out.println(actor.getEmail()+ "EMAIL");
		return actor.getEmail();
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
