package cristiannuenezjorge.progettosettimna3backend.payloads;

import cristiannuenezjorge.progettosettimna3backend.entities.Post;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostRespDTO(
        UUID id,
        String testo,
        LocalDateTime dataPubblicazione,
        AutoreDTO autore,
        long numeroLike
) {
    public static PostRespDTO da(Post post, long numeroLike) {
        return new PostRespDTO(
                post.getId(),
                post.getTesto(),
                post.getDataPubblicazione(),
                AutoreDTO.da(post.getAutore()),
                numeroLike
        );
    }
}