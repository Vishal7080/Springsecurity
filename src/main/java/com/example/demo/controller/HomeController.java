package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositry.UserRepos;
import com.example.demo.security.Entity.User;
import com.example.demo.service.JwtService;
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
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtService jwtService;

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
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user)
	{
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		if(authentication.isAuthenticated())
		{
			
			
//			return jwtService.generateToken(user.getUsername());
			return ResponseEntity.ok(jwtService.generateToken(user.getUsername()));
		}
		else
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	
	}
}
