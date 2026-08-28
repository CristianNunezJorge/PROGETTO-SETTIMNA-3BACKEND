package cristiannuenezjorge.progettosettimna3backend.services;

import cristiannuenezjorge.progettosettimna3backend.entities.Utente;
import cristiannuenezjorge.progettosettimna3backend.exceptions.ConflictException;
import cristiannuenezjorge.progettosettimna3backend.exceptions.UnauthorizedException;
import cristiannuenezjorge.progettosettimna3backend.payloads.LoginDTO;
import cristiannuenezjorge.progettosettimna3backend.payloads.NuovoUtenteDTO;
import cristiannuenezjorge.progettosettimna3backend.payloads.UtenteRespDTO;
import cristiannuenezjorge.progettosettimna3backend.repositories.UtenteRepository;
import cristiannuenezjorge.progettosettimna3backend.security.JWTTools;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTTools jwtTools;

    public AuthService(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder, JWTTools jwtTools) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTools = jwtTools;
    }

    @Transactional
    public UtenteRespDTO registra(NuovoUtenteDTO body) {
        if (utenteRepository.existsByEmail(body.email())) {
            throw new ConflictException("L'email " + body.email() + " e' gia' registrata");
        }
        if (utenteRepository.existsByUsername(body.username())) {
            throw new ConflictException("Lo username " + body.username() + " e' gia' in uso");
        }

        Utente nuovoUtente = new Utente(
                body.username(),
                body.nomeCompleto(),
                body.email(),
                passwordEncoder.encode(body.password())
        );
        return UtenteRespDTO.da(utenteRepository.save(nuovoUtente));
    }

    @Transactional(readOnly = true)
    public String login(LoginDTO body) {
        Utente utente = utenteRepository.findByEmail(body.email())
                .orElseThrow(() -> new UnauthorizedException("Credenziali non valide"));
        if (!passwordEncoder.matches(body.password(), utente.getPassword())) {
            throw new UnauthorizedException("Credenziali non valide");
        }

        return jwtTools.creaToken(utente);
    }
}