package cristiannuenezjorge.progettosettimna3backend.exceptions;

import cristiannuenezjorge.progettosettimna3backend.payloads.ErroreRespDTO;
import cristiannuenezjorge.progettosettimna3backend.payloads.ErroreValidazioneRespDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;


@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErroreRespDTO> gestisciNotFound(NotFoundException ex) {
        return costruisci(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErroreRespDTO> gestisciBadRequest(BadRequestException ex) {
        return costruisci(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErroreRespDTO> gestisciUnauthorized(UnauthorizedException ex) {
        return costruisci(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErroreRespDTO> gestisciConflict(ConflictException ex) {
        return costruisci(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErroreRespDTO> gestisciAccessDenied(AccessDeniedException ex) {
        return costruisci(HttpStatus.FORBIDDEN, "Non hai i permessi necessari per questa operazione");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroreValidazioneRespDTO> gestisciValidazione(MethodArgumentNotValidException ex) {
        List<String> errori = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(errore -> errore.getField() + ": " + errore.getDefaultMessage())
                .toList();

        ErroreValidazioneRespDTO body = new ErroreValidazioneRespDTO(
                "Alcuni campi non sono validi",
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                errori
        );
        return ResponseEntity.badRequest().body(body);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroreRespDTO> gestisciBodyIllegibile(HttpMessageNotReadableException ex) {
        return costruisci(HttpStatus.BAD_REQUEST,
                "Il corpo della richiesta non e' leggibile: controlla il JSON e i valori ammessi dei campi");
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroreRespDTO> gestisciViolazioneVincoli(DataIntegrityViolationException ex) {
        return costruisci(HttpStatus.CONFLICT, "Operazione in conflitto con un dato gia' presente");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroreRespDTO> gestisciGenerica(Exception ex) {
        ex.printStackTrace();
        return costruisci(HttpStatus.INTERNAL_SERVER_ERROR, "Errore interno del server");
    }

    private ResponseEntity<ErroreRespDTO> costruisci(HttpStatus status, String messaggio) {
        return ResponseEntity.status(status)
                .body(new ErroreRespDTO(messaggio, status.value(), LocalDateTime.now()));
    }
}