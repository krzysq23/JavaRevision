package revision.springDataJPA.infrastructure.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import revision.springDataJPA.infrastructure.database.entity.ProducerEntity;

@Repository
public interface ProducerDatabaseRepository extends JpaRepository<ProducerEntity, Integer> {


}
