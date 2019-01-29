package io.firestrap.oauthserver.controller;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.firestrap.oauthserver.config.AuthorityPropertyEditor;
import io.firestrap.oauthserver.config.SplitCollectionEditor;
import io.firestrap.oauthserver.entity.Credentials;


public class CredentialsController {

	
	
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Collection.class, new SplitCollectionEditor(Set.class, ","));
		binder.registerCustomEditor(GrantedAuthority.class, new AuthorityPropertyEditor());
	}
	


	
}
