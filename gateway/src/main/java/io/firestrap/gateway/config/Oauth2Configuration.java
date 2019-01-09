package io.firestrap.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
public class Oauth2Configuration extends WebSecurityConfigurerAdapter {

	@Autowired
	FireProps fireprops;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String[] arr=new String[fireprops.getAuthExcludeUrl().size()];
		http
		.csrf()
		.disable()
		.antMatcher("/**").authorizeRequests()
		.antMatchers(fireprops.getAuthExcludeUrl().toArray(arr)).permitAll()
		.anyRequest().authenticated().and().logout();
	}
}
