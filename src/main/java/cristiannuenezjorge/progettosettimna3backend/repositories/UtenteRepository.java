package cristiannuenezjorge.progettosettimna3backend.repositories;

import cristiannuenezjorge.progettosettimna3backend.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, UUID> {

    Optional<Utente> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}