package io.firestrap.oauthserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.firestrap.oauthserver.entity.Credentials;

public interface CredentialRepository extends JpaRepository<Credentials,Long> {
    Credentials findByName(String name);
}
