package io.firestrap.oauthserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import io.firestrap.oauthserver.entity.Approvals;



public interface ApprovalsRepository extends JpaRepository<Approvals,Long> {
	
	@Modifying
	@Transactional
	@Query("DELETE from Approvals  where userId=:userId")
	void deleteByUserId(@Param("userId") String userId);
}
