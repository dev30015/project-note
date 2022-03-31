package com.project.note.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/admin")
	public String admin() {
		return "admin access only";
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping(value="/hello")
	public String hello() {
		return "Hello World";
	}
	
}
