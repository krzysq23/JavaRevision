package revision.springDataJPA.infrastructure.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import revision.springDataJPA.infrastructure.database.entity.ProducerEntity;
import revision.springDataJPA.infrastructure.database.entity.PurchaseEntity;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface PurchaseDatabaseRepository extends JpaRepository<PurchaseEntity, Integer> {

    List<PurchaseEntity> findByDateTimeBetween(OffsetDateTime startDate, OffsetDateTime endDate);

}
