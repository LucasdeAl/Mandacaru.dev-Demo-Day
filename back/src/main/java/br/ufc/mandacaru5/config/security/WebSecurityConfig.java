package br.ufc.mandacaru5.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity	
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf()
			.disable();
		
		//http.sessionManagement()
        //.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
	}
	
	@Override
	public void configure(WebSecurity http) throws Exception {
		http
		.ignoring()
		.antMatchers(HttpMethod.POST, "/api/user")
		.antMatchers(HttpMethod.DELETE, "/api/properties/**")
		.antMatchers(HttpMethod.GET,"/api/user/**/properties")
		.antMatchers(HttpMethod.GET,"/api/user/**/properties/**")
		.antMatchers(HttpMethod.GET, "/api/properties", "/api/properties/**" )
		.antMatchers(HttpMethod.POST, "/api/posts/create");
//		.antMatchers("/api/login/**")
//		.antMatchers(HttpMethod.GET, "/api/posts")
//		.antMatchers("/api/roles/**")
//		.antMatchers(HttpMethod.OPTIONS, "/oauth/token")
//		.antMatchers("/v3/api-docs/**")
//		.antMatchers("/swagger-ui/**").antMatchers("/swagger-ui.html");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}	
}