package cristiannuenezjorge.progettosettimna3backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 1000)
    private String testo;

    @Column(name = "data_pubblicazione", nullable = false)
    private LocalDateTime dataPubblicazione;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "autore_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utente autore;

    public Post(String testo, Utente autore) {
        this.testo = testo;
        this.autore = autore;
        this.dataPubblicazione = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Post{testo='" + testo + "', dataPubblicazione=" + dataPubblicazione + ", id=" + id + "}";
    }
}

