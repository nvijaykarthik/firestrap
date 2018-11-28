package io.firestrap.gateway.zuulFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Configuration
public class RoutingFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(RoutingFilter.class);

	private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TOKEN_TYPE = "Bearer";
    
    
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		HttpServletResponse response=ctx.getResponse();
		 SecurityContext securityContext = SecurityContextHolder.getContext();
	     Authentication authentication = securityContext.getAuthentication();
	     
	     if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
	            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
	            ctx.addZuulRequestHeader(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, details.getTokenValue()));
	        }
	        
		log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}