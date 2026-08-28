package cristiannuenezjorge.progettosettimna3backend.controllers;

import cristiannuenezjorge.progettosettimna3backend.payloads.LoginDTO;
import cristiannuenezjorge.progettosettimna3backend.payloads.LoginRespDTO;
import cristiannuenezjorge.progettosettimna3backend.payloads.NuovoUtenteDTO;
import cristiannuenezjorge.progettosettimna3backend.payloads.UtenteRespDTO;
import cristiannuenezjorge.progettosettimna3backend.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UtenteRespDTO registra(@RequestBody @Valid NuovoUtenteDTO body) {
        return authService.registra(body);
    }

    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody @Valid LoginDTO body) {
        return new LoginRespDTO(authService.login(body));
    }
}