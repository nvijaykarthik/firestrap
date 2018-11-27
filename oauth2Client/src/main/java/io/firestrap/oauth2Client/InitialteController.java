package io.firestrap.oauth2Client;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitialteController {

	@RequestMapping("/user")
	public Principal getUser(Principal principal)
	{
		return principal;
	}
}
