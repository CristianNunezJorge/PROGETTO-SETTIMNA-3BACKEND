package cristiannuenezjorge.progettosettimna3backend.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String messaggio) {
        super(messaggio);
    }

    public NotFoundException(UUID id) {
        super("La risorsa con id " + id + " non e' stata trovata");
    }
}
