package io.firestrap.oauthserver.controller;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.firestrap.oauthserver.config.AuthorityPropertyEditor;
import io.firestrap.oauthserver.config.SplitCollectionEditor;
import io.firestrap.oauthserver.entity.Authority;
import io.firestrap.oauthserver.model.ResponseBean;
import io.firestrap.oauthserver.service.AuthorityService;


@RestController
@RequestMapping("/authority")
public class AuthorityController {

	
	@Autowired
	AuthorityService authorityService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Collection.class, new SplitCollectionEditor(Set.class, ","));
		binder.registerCustomEditor(GrantedAuthority.class, new AuthorityPropertyEditor());
	}
	

	@RequestMapping(value = "/getAuthority", method = RequestMethod.GET)	
	public List<Authority> getApprovals(Model model) {
		List<Authority> authorities = authorityService.getAuthorities();		
		return authorities;
	}
	
	@RequestMapping(value = "/addAuthority", method = RequestMethod.POST)	
	public ResponseBean addApprovals(@RequestBody List<String> authorities) {
		
		String statusMsg="";
		String statusCode="0";
		try {
		List<String> authority = authorityService.addAuthority(authorities);
		statusMsg="Successfully added  authorities : "+authorities;
		}catch(Exception e){
			statusMsg="Failed to add authorities";
			statusCode="-1";			
		}
		return new ResponseBean(statusMsg,statusCode);
	}
	
	@RequestMapping(value = "/editAuthority", method = RequestMethod.POST)	
	public ResponseBean editAuthorities(@RequestBody List<String> authorities) {
		
		String statusMsg="";
		String statusCode="0";
		try {
		List<String> authority = authorityService.addAuthority(authorities);
		statusMsg="Successfully added  authorities : "+authority;
		}catch(Exception e){
			statusMsg="Failed to add authorities";
			statusCode="-1";			
		}
		return new ResponseBean(statusMsg,statusCode);
	}
	
	@RequestMapping(value = "/deleteAuthority", method = RequestMethod.GET)	
	public ResponseBean deleteAuthority(@RequestParam String authorityId) {
		
		String statusMsg="";
		String statusCode="0";
		try {
			authorityService.deleteAuthority(authorityId);
		statusMsg="Successfully deleted the approval for the userId : "+authorityId;
		}catch(Exception e){
			statusMsg="Failed to delete approvals";
			statusCode="-1";
			e.printStackTrace();
		}
		return new ResponseBean(statusMsg,statusCode);
	}
	
}
