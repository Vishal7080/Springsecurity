package com.example.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	public UserDetailsService userDetailsService;
	
	
	@Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity s) throws Exception
	{
		 s
	        .csrf(csrf -> csrf.disable()) // Disables CSRF protection
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/home/**").permitAll() // Allows access to /home/greeting without authentication
	            .anyRequest().authenticated()// Requires authentication for all other requests
	        )
	        .httpBasic(Customizer.withDefaults())
	        .sessionManagement(m->m.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		 
		
	return s.build();
		
	}
	
//	@Bean
//	public UserDetailsService userDetailsService()
//	{
//		UserDetails u = User.withDefaultPasswordEncoder().username("vishal").password("123").roles("ADMIN").build();
//		
//		return new InMemoryUserDetailsManager(u);
//		
//	}
	
	
	@Bean 
	public AuthenticationManager authenticationManager(AuthenticationConfiguration c) throws Exception
	{
		return c.getAuthenticationManager();
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
