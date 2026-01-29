package revision.hibernate.code.oneToOne;

import jakarta.persistence.*;
import lombok.*;

@Data
@ToString(exclude = "customer")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "address")
    private String address;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "address", cascade = CascadeType.ALL)
    private Customer customer;
}
