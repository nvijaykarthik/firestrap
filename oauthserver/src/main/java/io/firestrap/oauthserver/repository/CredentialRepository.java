package io.firestrap.oauthserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import io.firestrap.oauthserver.entity.Credentials;

public interface CredentialRepository extends JpaRepository<Credentials,Long> {
    
	
	Credentials findByName(String name);
    
    
    @Modifying
	@Transactional
	@Query("DELETE from Credentials  where id=:id")
	void deleteByCredentialId(@Param("id") String credentialId);
}
