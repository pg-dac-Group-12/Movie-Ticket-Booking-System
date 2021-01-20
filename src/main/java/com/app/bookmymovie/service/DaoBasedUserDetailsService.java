package com.app.bookmymovie.service;



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
		System.out.println("Hello" + username);
		User user = userDao.findByEmail(username).get();
		if (user == null)
		{
			Theatre theatre = theatreDao.findByEmail(username).get();
			if(theatre == null)
				throw new UsernameNotFoundException("User by name " + username + " not found!");
			return new CustomUserDetails(theatre);
		}
		//to avoid lazy init exc
		//System.out.println(user.getRoles().size());
		return new CustomUserDetails(user);
	}

}
