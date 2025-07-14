package com.tweetapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tweetapp.service.MyUserDetailsService;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	MyUserDetailsService myUserDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(myUserDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.cors().and().csrf().disable().authorizeRequests()
//		.antMatchers("/api/v1.0/tweets/login", "/api/v1.0/tweets/register", "/api/v1.0/tweets/forgot")
//		.permitAll().anyRequest().authenticated().and().sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		/* For Testing Swagger */
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers("/api/v1.0/tweets/login", "/api/v1.0/tweets/register", "/api/v1.0/tweets/forgot",
						"/v2/api-docs", "/swagger-resources/**", "/swagger-ui/index.html", "/webjars/**",
						/* Probably not needed */ "/swagger.json", "/swagger-ui/swagger-ui.css",
						"/swagger-ui/swagger-ui.css.map", "/swagger-ui/index.css", "/swagger-ui-bundle.js",
						"/swagger-ui/swagger-ui-bundle.js", "/swagger-ui/swagger-ui-standalone-preset.js",
						"/swagger-ui/swagger-initializer.js", "/v3/api-docs/swagger-config",
						"/swagger-ui/favicon-32x32.png", "http://localhost:8087/swagger-ui/swagger-ui-bundle.js", "/actuator",
						"/v3/api-docs", "/api/v1.0/tweets/**")
				.permitAll().anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
