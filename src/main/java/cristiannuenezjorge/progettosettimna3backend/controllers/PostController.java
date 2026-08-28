package cristiannuenezjorge.progettosettimna3backend.controllers;

import cristiannuenezjorge.progettosettimna3backend.entities.Utente;
import cristiannuenezjorge.progettosettimna3backend.payloads.NuovoPostDTO;
import cristiannuenezjorge.progettosettimna3backend.payloads.PostRespDTO;
import cristiannuenezjorge.progettosettimna3backend.services.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostRespDTO crea(@RequestBody @Valid NuovoPostDTO body,
                            @AuthenticationPrincipal Utente utenteCorrente) {
        return postService.crea(body, utenteCorrente);
    }

    @GetMapping
    public List<PostRespDTO> trovaTutti() {
        return postService.trovaTutti();
    }

    @GetMapping("/{id}")
    public PostRespDTO trovaPerId(@PathVariable UUID id) {
        return postService.trovaPerId(id);
    }

    @PutMapping("/{id}")
    public PostRespDTO aggiorna(@PathVariable UUID id,
                                @RequestBody @Valid NuovoPostDTO body,
                                @AuthenticationPrincipal Utente utenteCorrente) {
        return postService.aggiorna(id, body, utenteCorrente);
    }
}