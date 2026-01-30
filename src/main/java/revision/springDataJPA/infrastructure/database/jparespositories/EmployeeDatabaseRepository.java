package revision.springDataJPA.infrastructure.database.jparespositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import revision.springDataJPA.infrastructure.database.entity.EmployeeEntity;

@Repository
public interface EmployeeDatabaseRepository extends JpaRepository<EmployeeEntity, Integer> {
}
