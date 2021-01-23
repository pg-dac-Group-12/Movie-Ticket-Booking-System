package com.app.bookmymovie.dto;

import com.app.bookmymovie.pojo.Actor;

public class AuthenticationResponse {
	private final String jwt;
	private final Actor actor;

	public AuthenticationResponse(String jwt, Actor actor) {
		super();
		this.jwt = jwt;
		this.actor = actor ;
	}

	public Actor getActor() {
		return actor;
	}

	public String getJwt() {
		return jwt;
	}
	
}
