package revision.springDataJPA.infrastructure.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import revision.springDataJPA.infrastructure.database.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductDatabaseRepository extends JpaRepository<ProductEntity, Integer> {


    List<ProductEntity> findByProductPriceGreaterThan(BigDecimal price);

    List<ProductEntity> findByProductPriceLessThan(BigDecimal price);

    List<ProductEntity> findByProductPriceBetween(BigDecimal startPrice, BigDecimal endPrice);

    List<ProductEntity> findByProductNameContaining(String name);

    List<ProductEntity> findByProductNameLike(String name);

}
