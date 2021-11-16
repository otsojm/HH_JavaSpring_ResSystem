package com.soft.ressystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
         .authorizeRequests().antMatchers("/h2-console/**").denyAll()
         .and()
         .authorizeRequests().antMatchers("/signup", "/saveuser", "/css/**").permitAll()
         .and()
         .authorizeRequests().anyRequest().authenticated()
         .and()
     .formLogin()
         .loginPage("/login")
         .defaultSuccessUrl("/gamelist")
         .permitAll()
         .and()
     .logout()
         .permitAll().invalidateHttpSession(true);
}	

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
