package cristiannuenezjorge.progettosettimna3backend.repositories;

import cristiannuenezjorge.progettosettimna3backend.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Like, UUID> {

    Optional<Like> findByPostIdAndUtenteId(UUID postId, UUID utenteId);

    boolean existsByPostIdAndUtenteId(UUID postId, UUID utenteId);

    long countByPostId(UUID postId);
}