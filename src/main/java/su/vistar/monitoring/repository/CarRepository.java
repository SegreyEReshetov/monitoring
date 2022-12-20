package su.vistar.monitoring.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import su.vistar.monitoring.entities.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Long> {
    Optional<Car> findByCarId(Long id);

    List<Car> findAll();
    List<Car> findAll(Pageable pageable);
    boolean existsCarByCarId(Long id);
}
