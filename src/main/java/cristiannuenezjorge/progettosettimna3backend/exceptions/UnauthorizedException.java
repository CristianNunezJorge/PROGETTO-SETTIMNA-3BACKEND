package cristiannuenezjorge.progettosettimna3backend.exceptions;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String messaggio) {
        super(messaggio);
    }
}