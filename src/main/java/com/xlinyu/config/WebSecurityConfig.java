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
		http
//			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "/home").permitAll()
			// 静态资源(css、js、img)
	    	.antMatchers("/assets/**").permitAll()
	    	
	    	.antMatchers("/account/signin").permitAll()
	    	.antMatchers("/login").permitAll()
	    	.antMatchers("/logout").permitAll()
	    	
	    	.antMatchers("/users/**").access("hasRole('ROLE_USER')")
	    	.antMatchers("/hello").access("hasRole('ROLE_ADMIN')")
	    	.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
	    	.anyRequest().authenticated()
	    .and()
//	    	.formLogin() // 默认的登录表单
//	    	指定登录表单 并使用默认的登录验证方式
	    	.formLogin().loginPage("/account/signin").loginProcessingUrl("/login")
	    .and()
	    	.logout().logoutSuccessUrl("/login?logout")	
	    .and()
			.exceptionHandling().accessDeniedPage("/account/403");
		
	}
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("u01").password("u01").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
	}
	
}
