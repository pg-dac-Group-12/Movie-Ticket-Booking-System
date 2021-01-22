package com.app.bookmymovie.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.bookmymovie.pojo.Theatre;
import com.app.bookmymovie.pojo.User;
import com.app.bookmymovie.repository.TheatreRepository;
import com.app.bookmymovie.repository.UserRepository;
import com.app.bookmymovie.util.CustomUserDetails;

@Service
@Transactional
public class DaoBasedUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userDao;
	
	@Autowired
	private TheatreRepository theatreDao ;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Hello wrld" + username);
		Optional<User> user = userDao.findByEmail(username);
		System.out.println("blah");
		System.out.println(user);
		if (!user.isPresent())
		{
			Optional<Theatre> theatre = theatreDao.findByEmail(username);
			System.out.println(theatre);
			if(!theatre.isPresent())
				throw new UsernameNotFoundException("User by name " + username + " not found!");
			return new CustomUserDetails(theatre.get());
		}
		System.out.println(user);
		//to avoid lazy init exc
		//System.out.println(user.getRoles().size());
		return new CustomUserDetails(user.get());
	}

}
