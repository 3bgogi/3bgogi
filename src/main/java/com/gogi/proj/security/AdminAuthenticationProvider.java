package com.gogi.proj.security;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminAuthenticationProvider implements AuthenticationProvider{

	private static final Logger logger = LoggerFactory.getLogger(AdminAuthenticationProvider.class);
	
	@Autowired
	private AdminServiceImpl adminServiceImple;
	
	@Autowired
	private PasswordEncoder passEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken)authentication;

		AdminVO adminvo = (AdminVO) adminServiceImple.loadUserByUsername(authToken.getName());

		if(adminvo == null) {
			
			throw new UsernameNotFoundException(authToken.getName());
			
		}

		if(!passEncoder.matches((CharSequence)authToken.getCredentials(), adminvo.getPassword())) {
			
			logger.info("Not matching AdminID or Password");
			
			throw new BadCredentialsException("Not matching adminName or Password");
			
		}
		
		List<GrantedAuthority> authorities = (List<GrantedAuthority>)adminvo.getAuthorities();
		
		return new UsernamePasswordAuthenticationToken(adminvo,null,authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
