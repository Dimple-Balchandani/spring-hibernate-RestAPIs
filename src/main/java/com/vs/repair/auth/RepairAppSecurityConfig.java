package com.vs.repair.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.vs.repair.utility.Constants;

@Configuration
@EnableWebSecurity
public class RepairAppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(Constants.AUTH_USER_NAME).password(Constants.AUTH_PASSWORD)
				.roles(Constants.AUTH_ROLE);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		if (Constants.USE_AUTH) {
			http.authorizeRequests().antMatchers("/**").hasRole(Constants.AUTH_ROLE).and().httpBasic()
					.realmName(Constants.REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
					.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
			http.csrf().disable();
		}
	}

	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
		return new CustomBasicAuthenticationEntryPoint();
	}

	/* To allow Pre-flight [OPTIONS] request from browser */
	@Override
	public void configure(WebSecurity web) throws Exception {
		System.out.println("RepairAppSecurityConfig.configure()");
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}
