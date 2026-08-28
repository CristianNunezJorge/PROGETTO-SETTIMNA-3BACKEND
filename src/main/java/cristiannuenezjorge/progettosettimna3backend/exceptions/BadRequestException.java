package cristiannuenezjorge.progettosettimna3backend.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String messaggio) {
        super(messaggio);
    }
}