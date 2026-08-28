package cristiannuenezjorge.progettosettimna3backend.payloads;

import java.util.UUID;

public record LikeRespDTO(UUID postId, long numeroLike, String messaggio) {
}