package com.upgrad.patterns.Authentication;

import com.upgrad.patterns.Middleware.AuthenticationProcessor;
import com.upgrad.patterns.Middleware.BasicAuthProcessor;
import com.upgrad.patterns.Middleware.JwtAuthProcessor;

import javax.servlet.http.HttpServletRequest;

public class Authenticator {
	
public static AuthenticationProcessor GetAuthProcessor()
	{
		// Create BasicAuthProcessor with null as next processor (end of chain)
		BasicAuthProcessor basicAuthProcessor = new BasicAuthProcessor(null);
		
		// Create JwtAuthProcessor with basicAuthProcessor as next processor
		JwtAuthProcessor jwtAuthProcessor = new JwtAuthProcessor(basicAuthProcessor);
		
		// Return the first processor in the chain
		return jwtAuthProcessor;
	}
	

    public static AuthenticationProvider GetAuthProvider(HttpServletRequest request)
    {
        if(request.getHeader("Authorization") != null)
            return new JwtAuthProvider(request.getHeader("Authorization"));
        return new BasicAuthProvider(request.getHeader("Username"), request.getHeader("Password"));
    }
}
