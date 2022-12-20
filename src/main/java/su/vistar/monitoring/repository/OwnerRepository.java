package su.vistar.monitoring.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import su.vistar.monitoring.entities.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Optional<Owner> findByOwnerId(Long id);

    List<Owner> findBySurnameAndNameAndPatronymic(String surname, String name, String patronymic);

    List<Owner> findAll();
    List<Owner> findAll(Pageable pageable);

    boolean existsOwnerByOwnerId(Long id);

}
