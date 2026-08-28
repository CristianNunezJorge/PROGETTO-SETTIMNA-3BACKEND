package cristiannuenezjorge.progettosettimna3backend.payloads;

import cristiannuenezjorge.progettosettimna3backend.entities.Ruolo;
import jakarta.validation.constraints.NotNull;

public record AggiornaRuoloDTO(
        @NotNull(message = "Il ruolo e' obbligatorio (valori ammessi: MEMBER, MODERATOR)")
        Ruolo ruolo
) {
}
