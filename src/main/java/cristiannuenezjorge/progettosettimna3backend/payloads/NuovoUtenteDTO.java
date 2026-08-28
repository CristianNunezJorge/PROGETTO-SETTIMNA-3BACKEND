package cristiannuenezjorge.progettosettimna3backend.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NuovoUtenteDTO(
        @NotBlank(message = "Lo username e' obbligatorio")
        @Size(min = 3, max = 30, message = "Lo username deve avere tra 3 e 30 caratteri")
        String username,

        @NotBlank(message = "Il nome completo e' obbligatorio")
        @Size(min = 3, max = 100, message = "Il nome completo deve avere tra 3 e 100 caratteri")
        String nomeCompleto,

        @NotBlank(message = "L'email e' obbligatoria")
        @Email(message = "L'email inserita non e' valida")
        String email,

        @NotBlank(message = "La password e' obbligatoria")
        @Size(min = 8, message = "La password deve avere almeno 8 caratteri")
        String password
) {
}
