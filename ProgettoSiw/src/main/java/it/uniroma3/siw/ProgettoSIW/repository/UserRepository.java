package it.uniroma3.siw.ProgettoSIW.repository;

import it.uniroma3.siw.ProgettoSIW.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
