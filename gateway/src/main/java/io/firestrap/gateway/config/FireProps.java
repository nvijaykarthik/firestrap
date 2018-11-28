package io.firestrap.gateway.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="io.fire")
public class FireProps {

	List<String> authExcludeUrl = new ArrayList<>();

	public List<String> getAuthExcludeUrl() {
		return authExcludeUrl;
	}

	public void setAuthExcludeUrl(List<String> authExcludeUrl) {
		this.authExcludeUrl = authExcludeUrl;
	}
	
}
