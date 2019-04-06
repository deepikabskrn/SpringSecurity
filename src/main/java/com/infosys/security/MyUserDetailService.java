package com.infosys.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.infosys.repository.UserRepo;
@Service
public class MyUserDetailService implements UserDetailsService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
System.out.println("user details....");
		com.infosys.entity.User user = userRepo.findByName(username);
			if (user == null) {
				throw new UsernameNotFoundException(username);
			}
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			//UserDetails userDetail = new User(user.getName(),passwordEncoder.encode(user.getPassword()), AuthorityUtils.createAuthorityList("ROLE_USER"));
			//return userDetail;
		  /*  UserBuilder builder = null;
		    if (user != null) {
		      builder = org.springframework.security.core.userdetails.User.withUsername(username);
		      builder.password(passwordEncoder.encode(user.getPassword()));
		      builder.roles("USER");
		    } else {
		      throw new UsernameNotFoundException("User not found.");
		    }

		    return builder.build();*/

			return new MyUserPrincipal(user);
}
		
}