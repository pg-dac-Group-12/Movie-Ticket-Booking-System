package com.app.bookmymovie.aspects;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.bookmymovie.annotation.Authorize;

@Component
@Aspect
public class Authorization {
	@Autowired
	HttpSession session ;
	
	@Before("@annotation(authorize)")
	public void authorize(Authorize authorize) throws Exception {
	 	String role = authorize.value();
	 	System.out.println("AOP");
	 	if(session.getAttribute("role") == null || !session.getAttribute("role").equals(role))
	 		throw new Exception("Not Logged In");
	}
}
