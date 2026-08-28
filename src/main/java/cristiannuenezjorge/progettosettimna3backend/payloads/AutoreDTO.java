package cristiannuenezjorge.progettosettimna3backend.payloads;

import cristiannuenezjorge.progettosettimna3backend.entities.Utente;

import java.util.UUID;

public record AutoreDTO(UUID id, String username, String nomeCompleto) {

    public static AutoreDTO da(Utente utente) {
        return new AutoreDTO(utente.getId(), utente.getUsername(), utente.getNomeCompleto());
    }
}