package io.firestrap.oauthserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import io.firestrap.oauthserver.entity.Authority;



public interface AuthorityRepository extends JpaRepository<Authority,Long> {

	
	@Modifying
	@Transactional
	@Query("DELETE from Authority  where id=:id")
	void deleteByAuthorityId(@Param("id") String authorityId);
}
