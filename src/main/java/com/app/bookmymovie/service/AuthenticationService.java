package com.app.bookmymovie.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.bookmymovie.dto.AuthenticationRequest;
import com.app.bookmymovie.dto.AuthenticationResponse;
import com.app.bookmymovie.pojo.Actor;
import com.app.bookmymovie.pojo.Role;
import com.app.bookmymovie.pojo.Theatre;
import com.app.bookmymovie.pojo.User;
import com.app.bookmymovie.repository.TheatreRepository;
import com.app.bookmymovie.repository.UserRepository;
import com.app.bookmymovie.util.JwtUtil;
@Service
@Transactional
public class AuthenticationService implements IAuthenticationService {
	@Autowired
	UserRepository userRepo ;
	@Autowired
	TheatreRepository theatreRepo ;
	@Autowired
	private AuthenticationManager mgr;
	@Autowired
	private UserDetailsService service;
	@Autowired
	private JwtUtil utils;
	@Autowired
	PasswordEncoder encoder ;

	@Override
	public Optional<User> authenticateUser(String email, String password) {
		Optional<User> user = userRepo.findByEmailAndPassword(email, password);
		return user;
	}
	
	public Optional<Theatre> authenticateTheatreAdmin(String email, String password) {
		Optional<Theatre> theatreAdmin = theatreRepo.findByEmailAndPassword(email, password);
		return theatreAdmin;
	}
	@Override
	public AuthenticationResponse changePassword(String oldPassword , String newPassword) {
		String emailId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		UserDetails userDetails = service.loadUserByUsername(emailId);
		if(!encoder.matches(oldPassword, userDetails.getPassword()))
			return null ;
		if(userDetails.getAuthorities().contains(new SimpleGrantedAuthority(Role.USER.toString()))) {
			User user = userRepo.findByEmail(userDetails.getUsername()).get();
			user.setPassword(encoder.encode(newPassword));
			userRepo.save(user);
		}
		else if(userDetails.getAuthorities().contains(new SimpleGrantedAuthority(Role.USER.toString()))) {
			Theatre theatre = theatreRepo.findByEmail(userDetails.getUsername()).get();
			theatre.setPassword(encoder.encode(newPassword));
			theatreRepo.save(theatre);
		}

		AuthenticationResponse resp = this.authenticateActor(new AuthenticationRequest(userDetails.getUsername(), newPassword));
		return resp ;
	}

	@Override
	public AuthenticationResponse authenticateActor(AuthenticationRequest req) {
		System.out.println(req.getPassword() + " User " + req.getUserName());
		try {
			mgr.authenticate(new UsernamePasswordAuthenticationToken
					(req.getUserName(), req.getPassword()));
		} catch (Exception e) {
			throw new RuntimeException("Invalid Email or password");
		}
		// authentication successful : return JWT token to the client
		UserDetails details = service.loadUserByUsername(req.getUserName());
		Actor usr_details = loadActorByUsername(req.getUserName());
		return new AuthenticationResponse(utils.generateToken(details), usr_details) ;
	}
	

	public Actor loadActorByUsername(String username) {
		System.out.println("Hello" + username);
		Optional<User> userOptional = userRepo.findByEmail(username);
		if (!userOptional.isPresent())
		{
			Optional<Theatre> theatreOptional= theatreRepo.findByEmail(username);
			if(!theatreOptional.isPresent())
				throw new UsernameNotFoundException("User by name " + username + " not found!");
			return theatreOptional.get();
		}
		//to avoid lazy init exc
		//System.out.println(user.getRoles().size());
		return userOptional.get();
	}
}