package revision.springDataJPA.infrastructure.database.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import revision.springDataJPA.infrastructure.database.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerDatabaseRepository extends JpaRepository<CustomerEntity, Integer> {

    @EntityGraph(
            type =  EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "purchaseEntities",
                    "purchaseEntities.opinionEntity"
            }
    )
    CustomerEntity findByEmail(String email);

    Optional<CustomerEntity> findByCustomerId(Integer id);

    List<CustomerEntity> findByNameOrEmail(String name, String email);

    List<CustomerEntity> findByNameAndEmail(String name, String email);

    CustomerEntity findDistinctByEmail(String email);

    List<CustomerEntity> findAllCustomers();

    CustomerEntity findCustomerByEmail(String email);

    List<CustomerEntity> findAllCustomersNNQ();

    CustomerEntity findCustomerByEmailNNQ(String email);
}
