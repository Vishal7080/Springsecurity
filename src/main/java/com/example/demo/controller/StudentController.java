package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

@GetMapping("/getstudent")
public List<String> getstudent()
{
	return List.of("vihal","giir");
}
	
	
}
