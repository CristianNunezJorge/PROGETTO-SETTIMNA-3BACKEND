package cristiannuenezjorge.progettosettimna3backend.security;

import cristiannuenezjorge.progettosettimna3backend.entities.Utente;
import cristiannuenezjorge.progettosettimna3backend.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTTools {

    private final SecretKey chiave;

    private static final long DURATA_MS = 1000L * 60 * 60 * 24 * 7;

    public JWTTools(@Value("${jwt.secret}") String secret) {
        this.chiave = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String creaToken(Utente utente) {
        long ora = System.currentTimeMillis();
        return Jwts.builder()
                .issuedAt(new Date(ora))
                .expiration(new Date(ora + DURATA_MS))
                .subject(utente.getId().toString())
                .claim("ruolo", utente.getRuolo().name())
                .signWith(chiave)
                .compact();
    }

    public String verificaTokenEdEstraiId(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(chiave)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (Exception ex) {
            throw new UnauthorizedException("Token non valido o scaduto: effettua di nuovo il login");
        }
    }
}