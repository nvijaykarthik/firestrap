package io.firestrap.oauthserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.firestrap.oauthserver.entity.Approvals;
import io.firestrap.oauthserver.entity.Authority;
import io.firestrap.oauthserver.repository.AuthorityRepository;

@Service
public class AuthorityService {
	
	@Autowired
	AuthorityRepository authorityRepository;

	
	public List<Authority> getAuthorities()
	{	
		List<Authority> getAuthorities= new ArrayList();
		getAuthorities=  authorityRepository.findAll();	
		
		return getAuthorities;				
	}
	
	public List<String> addAuthority(List<String> authority)
	{

		for(String auth : authority) {
			Authority addauthority= new Authority();
			addauthority.setAuthority(auth);
			authorityRepository.save(addauthority);	
		}

		return authority;				
	}
	
	public void deleteAuthority(String authorityId)
	{
		authorityRepository.deleteByAuthorityId(authorityId);					 			
	}
	
	
	
}
