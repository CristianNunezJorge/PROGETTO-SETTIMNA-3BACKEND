package cristiannuenezjorge.progettosettimna3backend.payloads;

import cristiannuenezjorge.progettosettimna3backend.entities.Ruolo;
import cristiannuenezjorge.progettosettimna3backend.entities.Utente;

import java.util.UUID;


public record UtenteRespDTO(
        UUID id,
        String username,
        String nomeCompleto,
        String email,
        Ruolo ruolo
) {
    public static UtenteRespDTO da(Utente utente) {
        return new UtenteRespDTO(
                utente.getId(),
                utente.getUsername(),
                utente.getNomeCompleto(),
                utente.getEmail(),
                utente.getRuolo()
        );
    }
}
