package io.firestrap.service.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;

@Configuration
@EnableResourceServer()
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .requestMatcher(new RequestHeaderRequestMatcher("Authorization"))
            .authorizeRequests().anyRequest().fullyAuthenticated();
    }

    /**
     * Oauth2 can control resource level
     * default resource id = oauth2-resource.
     * if we set the resource id here then oaut2 server should have the resource Ids Configured. 
     * Any way oauth server should have the "oauth2-resource"
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // resources.resourceId("service");
    }
}