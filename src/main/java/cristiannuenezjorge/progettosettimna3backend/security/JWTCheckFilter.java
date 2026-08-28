package cristiannuenezjorge.progettosettimna3backend.security;

import cristiannuenezjorge.progettosettimna3backend.entities.Utente;
import cristiannuenezjorge.progettosettimna3backend.exceptions.UnauthorizedException;
import cristiannuenezjorge.progettosettimna3backend.repositories.UtenteRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class JWTCheckFilter extends OncePerRequestFilter {

    private final JWTTools jwtTools;
    private final UtenteRepository utenteRepository;
    private final HandlerExceptionResolver exceptionResolver;

    public JWTCheckFilter(JWTTools jwtTools,
                          UtenteRepository utenteRepository,
                          HandlerExceptionResolver exceptionResolver) {
        this.jwtTools = jwtTools;
        this.utenteRepository = utenteRepository;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException {
        try {
            String authorization = request.getHeader("Authorization");

            if (authorization == null || !authorization.startsWith("Bearer ")) {
                throw new UnauthorizedException("Token mancante: inserisci l'header Authorization: Bearer <token>");
            }

            String token = authorization.substring(7);
            String idUtente = jwtTools.verificaTokenEdEstraiId(token);
            Utente utente = utenteRepository.findById(UUID.fromString(idUtente))
                    .orElseThrow(() -> new UnauthorizedException("L'utente collegato a questo token non esiste piu'"));

            var authentication = new UsernamePasswordAuthenticationToken(
                    utente,
                    null,
                    List.of(new SimpleGrantedAuthority(utente.getRuolo().name()))
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);

        } catch (Exception ex) {
            exceptionResolver.resolveException(request, response, null, ex);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().startsWith("/auth")
                || HttpMethod.OPTIONS.matches(request.getMethod());
    }
}