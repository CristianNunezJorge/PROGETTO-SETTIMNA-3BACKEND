package cristiannuenezjorge.progettosettimna3backend.payloads;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "L'email e' obbligatoria")
        String email,

        @NotBlank(message = "La password e' obbligatoria")
        String password
) {
}
