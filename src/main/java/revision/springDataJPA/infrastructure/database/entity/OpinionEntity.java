package revision.springDataJPA.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "opinionId")
@ToString(of = {"opinionId", "stars", "comment", "dateTime"})
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "opinion")
public class OpinionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "opinion_id", unique = true, nullable = false)
    private Integer opinionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    private PurchaseEntity purchaseEntity;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "stars")
    private Stars stars;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date_time")
    private LocalDateTime dateTime;
}
