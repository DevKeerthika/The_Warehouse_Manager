package com.jsp.whm.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jsp.whm.entity.Client;
import com.jsp.whm.exception.IllegalOperationException;
import com.jsp.whm.exception.UsernameNotFoundException;
import com.jsp.whm.repository.ClientRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter
{

	private ClientRepository clientRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		if(request.getSession(false) != null)
		{
			throw new IllegalOperationException("Illegal operation");
		}
		
		if(!request.getRequestURI().equals("/api/v1/client/register"))
		{

			String username = request.getHeader("USERNAME");
			String apiKey = request.getHeader("API-KEY");

			if(username != null && apiKey != null)
			{
				Client client = clientRepository.findByEmail(username)
						.orElseThrow(() -> new UsernameNotFoundException("Username not found"));

				if(!apiKey.equals(client.getApiKey()))
					throw new BadCredentialsException("Invalid Credentials");
			} 
			else 
				throw new UsernameNotFoundException("Username not found");
		}

		filterChain.doFilter(request, response);

	}

}
