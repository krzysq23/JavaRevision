package revision.springDataJPA.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import revision.springDataJPA.infrastructure.database.entity.CustomerEntity;
import revision.springDataJPA.infrastructure.database.entity.Stars;
import revision.springDataJPA.infrastructure.database.repository.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class QueryByExampleService {

    private final CustomerDatabaseRepository customerRepository;
    private final OpinionDatabaseRepository opinionDatabaseRepository;
    private final ProducerDatabaseRepository producerDatabaseRepository;
    private final ProductDatabaseRepository productDatabaseRepository;
    private final PurchaseDatabaseRepository purchaseDatabaseRepository;

    public void callCustomer() {

        System.out.println("###findByEmail(): " +
                customerRepository.findByEmail("nprati1m@exblog.jp"));
        System.out.println("###findByCustomerId(): " +
                customerRepository.findByCustomerId(45));
        System.out.println("###findByNameOrEmail(): " +
                customerRepository.findByNameOrEmail("Florian", "cwrightson1a@netvibes.com"));
        System.out.println("###findByNameAndEmail(): " +
                customerRepository.findByNameAndEmail("Florian", "fjertz15@amazon.cuk"));
        System.out.println("###findDistinctByEmail(): " +
                customerRepository.findDistinctByEmail("nprati1m@exblog.jp"));
        System.out.println("###findByEmail(null): " +
                customerRepository.findByEmail(null));
        System.out.println("###findByCustomerId(null): " +
                customerRepository.findByCustomerId(null));
        System.out.println("###findByNameOrEmail(\"Florian\", null): " +
                customerRepository.findByNameOrEmail("Florian", null));
        System.out.println("###findByNameAndEmail(null, \"test@gmail.com\"): " +
                customerRepository.findByNameAndEmail(null, "test@gmail.com"));
        System.out.println("###findDistinctByEmail(null): " +
                customerRepository.findDistinctByEmail(null));
    }

    public void callProducer() {
        BigDecimal price1 = BigDecimal.valueOf(32.11);
        BigDecimal price2 = BigDecimal.valueOf(54.22);
        System.out.println("###findByProductPriceGreaterThan(): " +
                productDatabaseRepository.findByProductPriceGreaterThan(price2));
        System.out.println("###findByProductPriceLessThan(): " +
                productDatabaseRepository.findByProductPriceLessThan(price1));
        System.out.println("###findByProductPriceBetween(): " +
                productDatabaseRepository.findByProductPriceBetween(price1, price2));
        System.out.println("###findByProductNameContaining(): " +
                productDatabaseRepository.findByProductNameContaining("Beef"));
        System.out.println("###findByProductNameLike(): " +
                productDatabaseRepository.findByProductNameLike("%Cookie"));
    }

    public void callPurchase() {
        OffsetDateTime date1 = OffsetDateTime.of(2016, 5, 29, 0, 0, 0, 0, ZoneOffset.UTC);
        OffsetDateTime date2 = OffsetDateTime.of(2017, 1, 2, 0, 0, 0, 0, ZoneOffset.UTC);
        System.out.println("###findByDateTimeBetween(): " +
                purchaseDatabaseRepository.findByDateTimeBetween(date1, date2));
    }

    public void callExample() {
        List<CustomerEntity> allCustomers = customerRepository.findAllCustomersNNQ();
        System.out.println("#ALL CUSTOMERS: " + allCustomers);
        CustomerEntity customerByEmail1 = customerRepository
                .findCustomerByEmailNNQ("bstilwell1k@deliciousdays.com");
        System.out.println("#CUSTOMER BY EMAIL: " + customerByEmail1);
    }

    @Transactional
    public void callQueryExample() {
        System.out.println("#findByCustomerEmail(): "
                + opinionDatabaseRepository.findByCustomerEmail("bmaddison1g@ebay.com"));
        System.out.println("#updateWrongOpinions() before: "
                + opinionDatabaseRepository.countIncorrectOpinions());
        opinionDatabaseRepository
                .updateWrongOpinions(Stars.FIVE, Set.of(Stars.ONE, Stars.TWO, Stars.THREE));
        System.out.println("#updateWrongOpinions() after: "
                + opinionDatabaseRepository.countIncorrectOpinions());
        System.out.println("#deleteOpinionsContaining() before: "
                + opinionDatabaseRepository.countOpinions());
        opinionDatabaseRepository.deleteOpinionsContaining("no");
        System.out.println("#deleteOpinionsContaining() after: "
                + opinionDatabaseRepository.countOpinions());
        System.out.println("#countMaxStars(): "
                + opinionDatabaseRepository.countMaxStars());
    }

    public void queryByExample() {

        CustomerEntity customerExample = CustomerEntity.builder().name("Florian").build();

        Example<CustomerEntity> example = Example.of(customerExample);

        List<CustomerEntity> result = customerRepository.findAll(example);

        System.out.println("#Found: " + result.size());
    }

    public void queryByExampleExampleMatcher() {

        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();

        CustomerEntity customerExample = CustomerEntity.builder().name("Florian").build();

        Example<CustomerEntity> example = Example.of(customerExample, caseInsensitiveExampleMatcher);

        List<CustomerEntity> result = customerRepository.findAll(example);

        System.out.println("#Found: " + result.size());
    }

    public void queryByExampleExampleMatcherCustom() {
        ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAll()
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase());

        CustomerEntity customerExample = CustomerEntity.builder()
                .email("com")
                .name("G")
                .build();

        Example<CustomerEntity> example = Example.of(customerExample, customExampleMatcher);

        List<CustomerEntity> result = customerRepository.findAll(example);

        System.out.println("#Found: " + result.size());
    }

}
