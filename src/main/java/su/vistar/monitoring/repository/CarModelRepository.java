package su.vistar.monitoring.repository;

import org.springframework.data.domain.Pageable;
import su.vistar.monitoring.entities.CarModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CarModelRepository extends CrudRepository<CarModel, Long> {
    Optional<CarModel> findByCarModelId(Long id);

    List<CarModel> findAll();
    List<CarModel> findAll(Pageable pageable);
    boolean existsCarModelByCarModelId(Long id);
}
