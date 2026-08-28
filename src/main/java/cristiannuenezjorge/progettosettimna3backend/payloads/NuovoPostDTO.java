package cristiannuenezjorge.progettosettimna3backend.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NuovoPostDTO(
        @NotBlank(message = "Il testo del post e' obbligatorio")
        @Size(min = 1, max = 1000, message = "Il testo puo' avere al massimo 1000 caratteri")
        String testo
) {
}