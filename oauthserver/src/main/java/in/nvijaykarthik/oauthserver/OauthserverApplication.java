package in.nvijaykarthik.oauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

@SpringBootApplication
public class OauthserverApplication extends AuthorizationServerConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(OauthserverApplication.class, args);
	}
}
