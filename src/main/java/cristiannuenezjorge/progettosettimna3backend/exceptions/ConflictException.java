package cristiannuenezjorge.progettosettimna3backend.exceptions;

public class ConflictException extends RuntimeException {

    public ConflictException(String messaggio) {
        super(messaggio);
    }
}