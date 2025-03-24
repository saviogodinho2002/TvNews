package godinho.savio.TvNews.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table( name = "mirrors" )
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mirror extends RepresentationModel<Mirror> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Temporal(TemporalType.DATE)
    @Column( nullable = false)
    private Date dateMirror;
}
