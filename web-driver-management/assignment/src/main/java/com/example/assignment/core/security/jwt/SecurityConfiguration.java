package com.example.assignment.core.security.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final CookieJwtAuthFilter cookieJwtAuthFilter;
	@Bean
	public SecurityFilterChain apisecurityFilterChain(HttpSecurity http) throws Exception{
		http
			.csrf()
			.disable()
			.authorizeHttpRequests()
			.requestMatchers("/","/images/**","/login/**", "/register/**", "/auth/**", "/drivers/**")
			.permitAll()
			.requestMatchers("/assignments/**", "/users/**").hasAuthority("ADMIN")
			.requestMatchers("/vehicles/**").hasAuthority("USER")
			.anyRequest()
			.authenticated()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.logout((logout) -> logout.deleteCookies("token"))
//				.formLogin()
//				.loginProcessingUrl("/login")
//				.defaultSuccessUrl("/")
//				.and()
			.authenticationProvider(authenticationProvider)
			.addFilterBefore(cookieJwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}


}
