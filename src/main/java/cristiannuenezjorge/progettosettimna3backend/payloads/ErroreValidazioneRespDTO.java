package cristiannuenezjorge.progettosettimna3backend.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErroreValidazioneRespDTO(
        String messaggio,
        int status,
        LocalDateTime timestamp,
        List<String> errori
) {
}