package com.example.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private CustomSuccessHandler customSuccessHandler;
	
	@Autowired
	private CustomFailHandler customFailHandler;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String login = "/login";
		String[] permitAll = {"/j_spring_security_login", login};
		String[] anyRoles = {"/home"};
		String[] adminRoles = {"/admin/**"};
		
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers(permitAll).permitAll()
			.antMatchers(anyRoles).hasAnyRole("USER","ADMIN")
			.antMatchers(adminRoles).hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling()
			.accessDeniedPage("/403");
		
		http.authorizeRequests()
			.and()
			.formLogin()
			.loginProcessingUrl("/j_spring_security_login")
			.loginPage(login)
			.successHandler(customSuccessHandler)
			.failureHandler(customFailHandler)
			.usernameParameter("username")
			.passwordParameter("password")
			.and()
			.logout()
			.logoutUrl("/j_spring_security_logout")
			.logoutSuccessUrl(login)
			.deleteCookies("JSESSIONID");
	}
	
	@Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/bower_components/**", "/dist/**");
    }

}
