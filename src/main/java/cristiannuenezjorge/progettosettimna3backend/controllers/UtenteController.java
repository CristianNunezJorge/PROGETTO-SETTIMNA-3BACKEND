package cristiannuenezjorge.progettosettimna3backend.controllers;

import cristiannuenezjorge.progettosettimna3backend.entities.Utente;
import cristiannuenezjorge.progettosettimna3backend.payloads.AggiornaRuoloDTO;
import cristiannuenezjorge.progettosettimna3backend.payloads.UtenteRespDTO;
import cristiannuenezjorge.progettosettimna3backend.services.UtenteService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    private final UtenteService utenteService;

    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @GetMapping("/me")
    public UtenteRespDTO profiloPersonale(@AuthenticationPrincipal Utente utenteCorrente) {
        return UtenteRespDTO.da(utenteCorrente);
    }

    @GetMapping
    public List<UtenteRespDTO> trovaTutti() {
        return utenteService.trovaTutti();
    }

    @GetMapping("/{id}")
    public UtenteRespDTO trovaPerId(@PathVariable UUID id) {
        return utenteService.trovaPerId(id);
    }

    @PatchMapping("/{id}/ruolo")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public UtenteRespDTO cambiaRuolo(@PathVariable UUID id, @RequestBody @Valid AggiornaRuoloDTO body) {
        return utenteService.cambiaRuolo(id, body);
    }
}