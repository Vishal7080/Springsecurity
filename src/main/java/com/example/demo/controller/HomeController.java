package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositry.UserRepos;
import com.example.demo.security.Entity.User;
import com.example.demo.service.MyUserDetailsService;

@RestController
@RequestMapping("/home")
public class HomeController {
	@Autowired
	MyUserDetailsService userDetailsService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserRepos userRepos;

	@GetMapping("/gretting")
	public  String hello()
	{
		return "Hello";
	}
	@PostMapping("/register")
	public User register(@RequestBody  User user)
	{
	
	User user1=new User();
		
		user1.setPassword(passwordEncoder.encode(user.getPassword()));
		user1.setUsername(user.username);
		return 	userRepos.save(user1);
	}
}
