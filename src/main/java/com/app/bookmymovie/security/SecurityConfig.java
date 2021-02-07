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
		
	
		http.csrf().disable().authorizeRequests().
		antMatchers("/images/**").permitAll().
		antMatchers("/validate").permitAll().
		antMatchers(HttpMethod.OPTIONS, "/**").permitAll().
		antMatchers("/swagger-ui.html*").permitAll().
		antMatchers("/favicon.ico").permitAll().
		antMatchers("/webjars/**").permitAll(). antMatchers("/api/*").permitAll().
		antMatchers("/swagger-resources/*").permitAll().
		antMatchers(HttpMethod.POST,"/login/").permitAll().
		antMatchers(HttpMethod.POST,"/ticket/cancel").hasAuthority(Role.USER.toString()).
		antMatchers(HttpMethod.GET,"/theatre/**/audis").hasAuthority(Role.THEATRE.toString()).
		antMatchers(HttpMethod.GET,"/shows/theatre/**").hasAnyAuthority(Role.USER.toString(), Role.THEATRE.toString()).
		antMatchers(HttpMethod.GET,"/theatre/**/audi/*").hasAnyAuthority(Role.THEATRE.toString()).
		antMatchers(HttpMethod.GET,"/shows/**/seatmap").hasAnyAuthority(Role.USER.toString(), Role.THEATRE.toString()).
		antMatchers(HttpMethod.GET,"/shows/audi/*").hasAnyAuthority(Role.USER.toString(), Role.THEATRE.toString()).
		//antMatchers("/**").permitAll(). // if (frust == true){ uncomment}
		antMatchers(HttpMethod.POST, "/user").permitAll().
		antMatchers(HttpMethod.POST, "/user/").permitAll().
		antMatchers(HttpMethod.POST, "/theatre/").permitAll().
		antMatchers(HttpMethod.POST, "/shows").hasAnyAuthority(Role.THEATRE.toString()).
		antMatchers(HttpMethod.PUT, "/shows/*").hasAnyAuthority(Role.THEATRE.toString()).
		antMatchers(HttpMethod.POST, "/shows/cancel").hasAnyAuthority(Role.THEATRE.toString()).
		antMatchers(HttpMethod.POST, "/theatre").hasAnyAuthority(Role.THEATRE.toString()).
		antMatchers("/theatre/*").hasAnyAuthority(Role.THEATRE.toString()).
		antMatchers(HttpMethod.GET, "/payment/*").hasAnyAuthority(Role.USER.toString()).
		antMatchers("/ticket*").hasAnyAuthority(Role.USER.toString()).
		antMatchers(HttpMethod.POST,"/ticket/*").hasAnyAuthority(Role.USER.toString()).
		antMatchers(HttpMethod.POST,"/ticket/cancel/*").hasAnyAuthority(Role.USER.toString()).
		antMatchers("/user/**").hasAnyAuthority(Role.USER.toString()).
		antMatchers(HttpMethod.GET,"/shows/audi/*").hasAnyRole(Role.USER.toString(), Role.THEATRE.toString()).
		antMatchers(HttpMethod.GET,"/shows/theatre/*").hasAnyRole(Role.USER.toString(), Role.THEATRE.toString()).
		antMatchers(HttpMethod.GET,"/ticket/*").hasAnyRole(Role.USER.toString(),Role.THEATRE.toString()).
		antMatchers(HttpMethod.POST,"/password/change").hasAnyRole(Role.USER.toString(), Role.THEATRE.toString()).
		antMatchers(HttpMethod.POST, "/shows/cancel").hasAnyAuthority(Role.THEATRE.toString()).
		antMatchers(HttpMethod.POST, "/payment/success/**").hasAnyAuthority(Role.USER.toString()).
		antMatchers("/user/*[0-9]").hasAnyAuthority(Role.USER.toString()).
		antMatchers(HttpMethod.GET,"/user").hasAnyAuthority(Role.USER.toString()).

		//antMatchers("/**").permitAll().
		antMatchers(HttpMethod.PUT,"/user/*").permitAll(). 
		antMatchers(HttpMethod.POST,"/user").permitAll().
		antMatchers(HttpMethod.POST,"/ticket/*").hasAnyAuthority(Role.USER.toString()).
		antMatchers(HttpMethod.GET,"/ticket/*").hasAnyRole(Role.USER.toString(),Role.THEATRE.toString()).
		antMatchers(HttpMethod.GET,"/ticket").hasAnyRole(Role.USER.toString()).
		antMatchers(HttpMethod.PUT, "/shows/*").hasAnyAuthority(Role.THEATRE.toString()).
		antMatchers(HttpMethod.POST, "/shows/*").hasAnyAuthority(Role.THEATRE.toString()).
		antMatchers("/theatre/*").hasAnyAuthority(Role.THEATRE.toString()).
		antMatchers(HttpMethod.POST, "/theatre").permitAll().
//		
		//no auth
		antMatchers(HttpMethod.GET,"/movie/*").permitAll().
		antMatchers(HttpMethod.GET,"/movie").permitAll().
		antMatchers("/login").permitAll().
//
		antMatchers("/password/*").permitAll().	
		antMatchers(HttpMethod.GET,"/shows").permitAll().
		antMatchers(HttpMethod.GET,"/shows/*").permitAll().

//		
//		//site admin shit. for now denying all
		antMatchers(HttpMethod.POST,"/movie/upload/*").denyAll().
		antMatchers(HttpMethod.DELETE,"/movie/*").denyAll().
		antMatchers(HttpMethod.PUT,"/movie/*").denyAll().
		antMatchers(HttpMethod.POST,"/movie").denyAll().
//		/*
//				 * allow OPTIONS call here for angular. These OPTIONS call are made by Angular
//				 * application to Spring Boot application.
//				 */
		antMatchers("/password/*").permitAll().
		antMatchers("/logoff").permitAll().
		antMatchers(HttpMethod.GET,"/movie").permitAll().
		antMatchers(HttpMethod.GET, "/movie/*").permitAll().
		antMatchers(HttpMethod.GET, "/shows").permitAll().
		antMatchers(HttpMethod.GET, "/shows/*").permitAll().
		antMatchers(HttpMethod.GET, "/shows/showId/seatmap").permitAll().
		antMatchers(HttpMethod.GET,"/shows/audi/*").hasAnyRole(Role.USER.toString(),Role.THEATRE.toString()).
		antMatchers(HttpMethod.GET,"/shows/theatre/*").hasAnyRole(Role.USER.toString(),Role.THEATRE.toString()).
		antMatchers(HttpMethod.GET,"/ticket/*").hasAnyRole(Role.USER.toString(),Role.THEATRE.toString()).
		antMatchers("/movie").hasAnyRole(Role.SITE.toString()).
		antMatchers("/movie/*").hasAnyRole(Role.SITE.toString()).
		antMatchers(HttpMethod.POST,"/shows").hasAnyRole(Role.THEATRE.toString()).
		antMatchers(HttpMethod.PUT,"/shows/*").hasAnyRole(Role.THEATRE.toString()).
		antMatchers(HttpMethod.POST,"/shows/cancel").hasAnyRole(Role.THEATRE.toString()).
		antMatchers(HttpMethod.POST,"/theatre").hasAnyRole(Role.THEATRE.toString()).
		antMatchers("/theatre/*").hasAnyRole(Role.THEATRE.toString()).
		antMatchers("/payment/*").hasAnyRole(Role.USER.toString()).
		antMatchers("/ticket").hasAnyRole(Role.USER.toString()).
		antMatchers("/ticket/*").hasAnyRole(Role.USER.toString()).	

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
