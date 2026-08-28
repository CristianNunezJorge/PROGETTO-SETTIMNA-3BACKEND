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
@Table(
        name = "likes",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_like_post_utente",
                columnNames = {"post_id", "utente_id"}
        )
)
@Getter
@Setter
@NoArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Utente utente;

    @Column(name = "data_like", nullable = false)
    private LocalDateTime dataLike;

    public Like(Post post, Utente utente) {
        this.post = post;
        this.utente = utente;
        this.dataLike = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Like{dataLike=" + dataLike + ", id=" + id + "}";
    }
}