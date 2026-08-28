package cristiannuenezjorge.progettosettimna3backend.payloads;

import java.time.LocalDateTime;

public record ErroreRespDTO(String messaggio, int status, LocalDateTime timestamp) {
}