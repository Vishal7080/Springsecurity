package com.example.demo.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET= "qwertyuiopasdfghjklzxcvbnmqwertyuioytdshgdtgshpasdfghjklzxcvbnm12345678901234567890123456789012";


	
	public String generateToken(String username)
	{
		
		Map<String,Object> claims=new HashMap<>();
		return Jwts.builder()
			    .setSubject(username)
			    .setClaims(claims)
			    .setIssuedAt(new Date(System.currentTimeMillis()))
			    .setExpiration(new Date(System.currentTimeMillis() + 1000*6*3)) // Set expiration time
			    .signWith( getKey(),SignatureAlgorithm.HS512) // Sign with algorithm and secret key
			    .compact(); // Finaliz
	}
	
	
	public Key getKey()
	{
		byte[] keybites =Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keybites);
	}
}
