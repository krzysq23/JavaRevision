package revision.hibernate.code.oneToMany;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class OwnerRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public Owner insertData(final Owner owner, final Set<Pet> pets) {
        Session session = sessionFactory.getCurrentSession();
        pets.forEach(pet -> pet.setOwner(owner));
        session.persist(owner);
        return owner;
    }

    public List<Owner> listOwners() {
        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT owner FROM Owner owner";
        List<Owner> owners = session.createQuery(query, Owner.class).list();
        return owners;
    }

}
