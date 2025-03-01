package com.airlinq.Project_Informica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.airlinq.Project_Informica.model.JwtRequest;
import com.airlinq.Project_Informica.model.JwtResponse;
import com.airlinq.Project_Informica.service.authenticate_service.AuthenticateServiceImpl;

/**
 * This AuthenticateController class is a controller class to accept authorization requests.
 * This controller class accepts all the HTTP requests for authentication and returns token.
 * 
 * @author Mahi Kumawat 
 * @version 1.0
 *
 */

@RestController
public class AuthenticateController {

	@Autowired
	private AuthenticateServiceImpl authenticateServiceImpl;
	
	/**
	 * API for token creation
	 * 
	 * @param jwtRequest
	 * @throws Exception
	 */
	@PostMapping("/authenticate")
	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
		
		return this.authenticateServiceImpl.authenticate(jwtRequest);
	}
	
}
