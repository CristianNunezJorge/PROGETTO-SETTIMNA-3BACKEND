package cristiannuenezjorge.progettosettimna3backend.controllers;

import cristiannuenezjorge.progettosettimna3backend.entities.Utente;
import cristiannuenezjorge.progettosettimna3backend.payloads.LikeRespDTO;
import cristiannuenezjorge.progettosettimna3backend.services.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/post/{postId}/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LikeRespDTO aggiungiLike(@PathVariable UUID postId,
                                    @AuthenticationPrincipal Utente utenteCorrente) {
        return likeService.aggiungiLike(postId, utenteCorrente);
    }

    @DeleteMapping
    public LikeRespDTO rimuoviLike(@PathVariable UUID postId,
                                   @AuthenticationPrincipal Utente utenteCorrente) {
        return likeService.rimuoviLike(postId, utenteCorrente);
    }
}