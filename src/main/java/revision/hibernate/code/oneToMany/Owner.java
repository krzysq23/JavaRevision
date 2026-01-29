package revision.hibernate.code.oneToMany;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "owner")
@NamedQueries({
        @NamedQuery(
                name = "Owner.findAll",
                query = "FROM Owner"),
        @NamedQuery(
                name = "Owner.findOwnerByEmail",
                query = "FROM Owner WHERE email = :email"
        )
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Owner.findAllNative",
                query = "SELECT * FROM Owner",
                resultClass = Owner.class
        )
})
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="owner_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SELECT)
    @BatchSize(size = 3)
    @OrderBy("breed ASC") // Baza danych sortuje dane szybciej
    private Set<Pet> pets;

    @Override
    public String toString() {
        return "Owner(id=" + this.getId() + ", name=" + this.getName()
                + ", surname=" + this.getSurname() + ", phone=" + this.getPhone()
                + ", email=" + this.getEmail() + ", pets=" + this.getPets() + ")";
    }

}
