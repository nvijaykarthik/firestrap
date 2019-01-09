package io.firestrap.oauthserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.firestrap.oauthserver.entity.Credentials;
import io.firestrap.oauthserver.repository.CredentialRepository;


public class CredentailsService {

	
	@Autowired
	CredentialRepository credentialsRepository;

	
	public List<Credentials> getCredentials()
	{	
		List<Credentials> getAuthorities= new ArrayList();
		getAuthorities=  credentialsRepository.findAll();	
		
		return getAuthorities;				
	}
	
	public List<Credentials> addCredentials(List<Credentials> credential)
	{

		for(Credentials cred : credential) {
			Credentials addcredentials= new Credentials();
			
			addcredentials.setName(cred.getName());
			addcredentials.setEnabled(cred.isEnabled());
			addcredentials.setPassword(cred.getPassword());
			addcredentials.setVersion(cred.getVersion());
			
			credentialsRepository.save(addcredentials);	
		}

		return credential;				
	}
	
	public void deleteAuthority(String credentialId)
	{
		credentialsRepository.deleteByCredentialId(credentialId);					 			
	}
}
