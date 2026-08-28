package cristiannuenezjorge.progettosettimna3backend.services;

import cristiannuenezjorge.progettosettimna3backend.entities.Like;
import cristiannuenezjorge.progettosettimna3backend.entities.Post;
import cristiannuenezjorge.progettosettimna3backend.entities.Utente;
import cristiannuenezjorge.progettosettimna3backend.exceptions.ConflictException;
import cristiannuenezjorge.progettosettimna3backend.exceptions.NotFoundException;
import cristiannuenezjorge.progettosettimna3backend.payloads.LikeRespDTO;
import cristiannuenezjorge.progettosettimna3backend.repositories.LikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;

    public LikeService(LikeRepository likeRepository, PostService postService) {
        this.likeRepository = likeRepository;
        this.postService = postService;
    }

    @Transactional
    public LikeRespDTO aggiungiLike(UUID postId, Utente utente) {
        Post post = postService.trovaEntitaPerId(postId);
        if (likeRepository.existsByPostIdAndUtenteId(postId, utente.getId())) {
            throw new ConflictException("Hai gia' messo like a questo post");
        }

        likeRepository.save(new Like(post, utente));
        return new LikeRespDTO(postId, likeRepository.countByPostId(postId), "Like aggiunto");
    }

    @Transactional
    public LikeRespDTO rimuoviLike(UUID postId, Utente utente) {
        Like like = likeRepository.findByPostIdAndUtenteId(postId, utente.getId())
                .orElseThrow(() -> new NotFoundException("Non hai messo like a questo post"));

        likeRepository.delete(like);
        return new LikeRespDTO(postId, likeRepository.countByPostId(postId), "Like rimosso");
    }
}