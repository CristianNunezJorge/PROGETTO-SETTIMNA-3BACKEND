package cristiannuenezjorge.progettosettimna3backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(nullable = false, unique = true)
    private String email;

    // Qui viene salvato l'hash BCrypt, mai la password in chiaro
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Ruolo ruolo;

    public Utente(String username, String nomeCompleto, String email, String password) {
        this.username = username;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.password = password;
        this.ruolo = Ruolo.MEMBER;
    }

    @Override
    public String toString() {
        return "Utente{username='" + username + "', nomeCompleto='" + nomeCompleto
                + "', email='" + email + "', ruolo=" + ruolo + ", id=" + id + "}";
    }
}