package br.com.cast.avaliacao.repository;

import br.com.cast.avaliacao.model.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSystemRepository extends JpaRepository<UserSystem, Long> {

    Optional<UserSystem> findByLogin(final String login);
}
