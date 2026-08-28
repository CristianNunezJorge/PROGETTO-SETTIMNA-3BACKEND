package cristiannuenezjorge.progettosettimna3backend.services;

import cristiannuenezjorge.progettosettimna3backend.entities.Post;
import cristiannuenezjorge.progettosettimna3backend.entities.Ruolo;
import cristiannuenezjorge.progettosettimna3backend.entities.Utente;
import cristiannuenezjorge.progettosettimna3backend.exceptions.NotFoundException;
import cristiannuenezjorge.progettosettimna3backend.payloads.NuovoPostDTO;
import cristiannuenezjorge.progettosettimna3backend.payloads.PostRespDTO;
import cristiannuenezjorge.progettosettimna3backend.repositories.LikeRepository;
import cristiannuenezjorge.progettosettimna3backend.repositories.PostRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public PostService(PostRepository postRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
    }

    @Transactional
    public PostRespDTO crea(NuovoPostDTO body, Utente autore) {
        Post post = new Post(body.testo(), autore);
        Post salvato = postRepository.save(post);
        return PostRespDTO.da(salvato, 0);
    }

    @Transactional(readOnly = true)
    public List<PostRespDTO> trovaTutti() {
        return postRepository.findAllByOrderByDataPubblicazioneDesc()
                .stream()
                .map(post -> PostRespDTO.da(post, likeRepository.countByPostId(post.getId())))
                .toList();
    }

    @Transactional(readOnly = true)
    public PostRespDTO trovaPerId(UUID id) {
        Post post = trovaEntitaPerId(id);
        return PostRespDTO.da(post, likeRepository.countByPostId(id));
    }

    @Transactional
    public PostRespDTO aggiorna(UUID id, NuovoPostDTO body, Utente richiedente) {
        Post post = trovaEntitaPerId(id);

        boolean eAutore = post.getAutore().getId().equals(richiedente.getId());
        boolean eModeratore = richiedente.getRuolo() == Ruolo.MODERATOR;
        if (!eAutore && !eModeratore) {
            throw new AccessDeniedException("Puoi modificare solo i post che hai scritto");
        }

        post.setTesto(body.testo());
        Post aggiornato = postRepository.save(post);
        return PostRespDTO.da(aggiornato, likeRepository.countByPostId(id));
    }

    public Post trovaEntitaPerId(UUID id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nessun post trovato con id " + id));
    }
}