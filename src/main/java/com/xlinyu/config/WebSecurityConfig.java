package com.xlinyu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/home").permitAll()
			// 静态资源(css、js、img)
	    	.antMatchers("/assets/**").permitAll()
	    	.antMatchers("/account/signin").permitAll()
	    	.antMatchers("/users/**").access("hasRole('ROLE_USER')")
	    	.antMatchers("/hello").access("hasRole('ROLE_ADMIN')")
	    	.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
	    	.anyRequest().authenticated()
	    .and()
	    	.formLogin().loginPage("/account/signin").failureUrl("/account/signin")
	    .and()
	    	.logout().permitAll();
		
	}
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("u01").password("u01").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
	}
	
}
