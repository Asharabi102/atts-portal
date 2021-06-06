package com.asharabi.atts.portal.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import com.asharabi.atts.portal.jwt.JwtRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//@Autowired
	//private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().cors()
			.and()
				.authorizeRequests().antMatchers("/").permitAll()
			.and()
				.authorizeRequests().antMatchers("/login").permitAll()
			.and()
				.authorizeRequests().antMatchers("/register").permitAll();
			/*.and()
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
		
	//	http.sessionManagement().invalidSessionStrategy((request, response) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session ended!")); 

	}
}
