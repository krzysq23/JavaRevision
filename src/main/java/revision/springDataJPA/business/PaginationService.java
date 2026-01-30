package revision.springDataJPA.business;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import revision.springDataJPA.infrastructure.database.entity.CustomerEntity;
import revision.springDataJPA.infrastructure.database.repository.CustomerDatabaseRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PaginationService {

    private final CustomerDatabaseRepository customerRepository;

    public void paginationExample() {
        paginate(0, 10);
        paginate(1, 10);
        paginate(2, 10);
        paginate(4, 10);
        paginate(8, 10);
        paginate(12, 10);
    }

    private void paginate(int pageNumber, int pageSize) {

        System.out.printf("#### PAGINATION. PN: %s, PS: %s ####%n", pageNumber, pageSize);

        Sort sort = Sort.by("name").ascending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<CustomerEntity> page = customerRepository.findAll(pageable);

        List<CustomerEntity> content = page.getContent();

        if (content.isEmpty()) {
            System.out.println("###PAGE IS EMPTY!");
        }
        content.forEach(customer -> System.out.println("###Customer: " + customer));

        System.out.println("###page.getTotalPages(): " + page.getTotalPages());
        System.out.println("###page.getTotalElements(): " + page.getTotalElements());
        System.out.println("###page.getNumberOfElements(): " + page.getNumberOfElements());
        System.out.println("###page.getSize(): " + page.getSize());
        System.out.println("###page.isLast(): " + page.isLast());
        System.out.println("###page.isFirst(): " + page.isFirst());
    }
}
