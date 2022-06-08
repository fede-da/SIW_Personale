package it.uniroma3.siw.ProgettoSIW.repository;

import it.uniroma3.siw.ProgettoSIW.model.Credentials;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CredentialsRepository extends CrudRepository<Credentials, Long> {
    public Optional<Credentials> findByUsername(String username);
}
