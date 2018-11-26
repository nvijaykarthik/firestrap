package in.nvijaykarthik.oauthserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nvijaykarthik.oauthserver.entity.Credentials;

public interface CredentialRepository extends JpaRepository<Credentials,Long> {
    Credentials findByName(String name);
}
