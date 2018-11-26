package in.nvijaykarthik.oauthserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import in.nvijaykarthik.oauthserver.entity.Credentials;
import in.nvijaykarthik.oauthserver.repository.CredentialRepository;

public class DataBaseUserDetails implements UserDetailsService {

	
	private static final Logger log = LoggerFactory.getLogger(DataBaseUserDetails.class);

	 @Autowired
	    private CredentialRepository credentialRepository;
	 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Authenticating {}",username );

		Credentials credentials = credentialRepository.findByName(username);
		log.info("Credentials {} ",credentials);

        if(credentials==null){

            throw new UsernameNotFoundException("User"+username+"can not be found");
        }

        User user = new User(credentials.getName(),credentials.getPassword(),credentials.isEnabled(),true,true,true,credentials.getAuthorities());

        return  user;
	}

}
