package com.jsp.whm.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jsp.whm.entity.Client;
import com.jsp.whm.exception.UsernameNotFoundException;
import com.jsp.whm.repository.ClientRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApiKeyFilter extends OncePerRequestFilter
{
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		String username = request.getHeader("USERNAME");
		String apiKey = request.getHeader("API-KEY");
		
		if(username == null && apiKey == null)
			throw new UsernameNotFoundException("Username not found");
		else
		{
			Client client = clientRepository.findByEmail(username);
			String clientEmail = client.getEmail();
			String clientApiKey = client.getApiKey();
			
			if(clientEmail == username && apiKey == clientApiKey)
				filterChain.doFilter(request, response);
			else
				throw new BadCredentialsException("Bad credentials");
		}
	}

}
