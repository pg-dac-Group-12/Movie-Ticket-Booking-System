package com.app.bookmymovie.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.bookmymovie.pojo.Role;
import com.app.bookymymovie.filters.JwtRequestFilter;

@ComponentScan({ "com.app.bookmymovie.*" })
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService service;
	@Autowired
	private JwtRequestFilter filter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// disable CSRF , no auth required for /authenticate & for any other request ,
		// its required.
		
	
		http.cors().and().csrf().disable().authorizeRequests().

		/*
		 * antMatchers("/swagger-ui.html*").permitAll().
		 * antMatchers("/favicon.ico").permitAll().
		 * antMatchers("/webjars/**").permitAll(). antMatchers("/api/*").permitAll().
		 * antMatchers("/swagger-resources/*").permitAll().
		 */
		//We are using this now because we(I) still haven't figured out this shit yet
		//antMatchers("/**").permitAll(). 
		
		//does not work. Still to be figured out.
		antMatchers(HttpMethod.POST,"/ticket/cancel/*[0-9]").hasRole(Role.USER.toString()).
		antMatchers(HttpMethod.GET,"/shows/theatre/*[0-9]").hasAnyRole(Role.USER.toString(), Role.THEATRE.toString()).
		antMatchers(HttpMethod.GET,"/theatre/*[0-9]/audis").hasRole(Role.THEATRE.toString()).
		antMatchers(HttpMethod.GET,"/theatre/*[0-9]/audi/*").hasRole(Role.THEATRE.toString()).
		antMatchers(HttpMethod.GET,"/shows/*[0-9]/seatmap").permitAll().
		antMatchers(HttpMethod.GET,"/shows/audi/*[0-9]").hasAnyRole(Role.USER.toString(), Role.THEATRE.toString()).
		antMatchers("/**").permitAll(). // if (frust == true){ uncomment}
		antMatchers(HttpMethod.POST, "/user").permitAll().
		antMatchers(HttpMethod.POST, "/shows").hasRole(Role.THEATRE.toString()).
		antMatchers(HttpMethod.PUT, "/shows/{id}").hasRole(Role.THEATRE.toString()).
		antMatchers(HttpMethod.POST, "/shows/cancel").hasRole(Role.THEATRE.toString()).
		antMatchers(HttpMethod.POST, "/theatre").hasRole(Role.THEATRE.toString()).
		antMatchers("/theatre/*").hasRole(Role.THEATRE.toString()).
		antMatchers(HttpMethod.GET, "/payment/*").hasRole(Role.USER.toString()).
		antMatchers("/ticket*").hasRole(Role.USER.toString()).
		antMatchers(HttpMethod.POST,"/ticket/{showId}").hasRole(Role.USER.toString()).
		antMatchers(HttpMethod.POST,"/ticket/cancel/{ticketId}").hasRole(Role.USER.toString()).
		antMatchers("/user*").hasRole(Role.USER.toString()).
		antMatchers(HttpMethod.GET,"/shows/audi/{audiId}").hasAnyRole(Role.USER.toString(), Role.THEATRE.toString()).
		antMatchers(HttpMethod.GET,"/shows/theatre/{theatreID}").hasAnyRole(Role.USER.toString(), Role.THEATRE.toString()).
		antMatchers(HttpMethod.GET,"/ticket/{id}").hasAnyRole(Role.USER.toString(),Role.THEATRE.toString()).
		antMatchers(HttpMethod.POST,"/password/change").hasAnyRole(Role.USER.toString(), Role.THEATRE.toString()).
		antMatchers(HttpMethod.POST, "/shows/cancel").hasRole(Role.THEATRE.toString()).
		antMatchers(HttpMethod.GET, "/payment/success").hasRole(Role.USER.toString()).
		antMatchers("/user/*[0-9]").hasRole(Role.USER.toString()).
		antMatchers(HttpMethod.GET,"/user").hasRole(Role.USER.toString()).
		antMatchers(HttpMethod.POST,"/user").permitAll().
		antMatchers(HttpMethod.POST,"/ticket/*[0-9]").hasRole(Role.USER.toString()).
		antMatchers(HttpMethod.GET,"/ticket/*[0-9]").hasAnyRole(Role.USER.toString(),Role.THEATRE.toString()).
		antMatchers(HttpMethod.GET,"/ticket").hasAnyRole(Role.USER.toString()).
		antMatchers(HttpMethod.PUT, "/shows/*[0-9]").hasRole(Role.THEATRE.toString()).
		antMatchers(HttpMethod.POST, "/shows**").hasRole(Role.THEATRE.toString()).
		antMatchers("/theatre/*[0-9]").hasRole(Role.THEATRE.toString()).
		antMatchers(HttpMethod.POST, "/theatre").permitAll().
		
		//no auth
		antMatchers(HttpMethod.GET,"/movie/*[0-9]").permitAll().
		antMatchers(HttpMethod.GET,"/movie").permitAll().
		antMatchers("/login").permitAll().
		antMatchers("/password/*").permitAll().	
		antMatchers(HttpMethod.GET,"/shows").permitAll().
		antMatchers(HttpMethod.GET,"/shows/{id}").permitAll().

		
		//site admin shit. for now denying all
		antMatchers(HttpMethod.POST,"/movie/upload/*[0-9]").denyAll().
		antMatchers(HttpMethod.DELETE,"/movie/*[0-9]").denyAll().
		antMatchers(HttpMethod.PUT,"/movie/*[0-9]").denyAll().
		antMatchers(HttpMethod.POST,"/movie").denyAll().
		/*
				 * allow OPTIONS call here for angular. These OPTIONS call are made by Angular
				 * application to Spring Boot application.
				 */

		antMatchers(HttpMethod.OPTIONS, "/**").permitAll().

		anyRequest().authenticated().and().sessionManagement().
		sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
