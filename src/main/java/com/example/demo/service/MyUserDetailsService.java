package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.repositry.UserRepos;
import com.example.demo.security.Entity.User;
import com.example.demo.security.Entity.UserPrincipal;

@Component
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepos usreRepos;

	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=usreRepos.findByUsername(username);
		
		if(user==null)
		{
			System.out.println("user is null");
			throw new UsernameNotFoundException("402");
		}
		System.out.println(user.getPassword());
	
		return new UserPrincipal(user);
	}
	

}
