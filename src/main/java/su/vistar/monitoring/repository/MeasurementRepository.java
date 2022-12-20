package su.vistar.monitoring.repository;

import su.vistar.monitoring.entities.Measurement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MeasurementRepository extends CrudRepository<Measurement, Long> {
    Optional<Measurement> findByMeasurementId(Long id);

    List<Measurement> findAll();

    boolean existsMeasurementByMeasurementId(Long id);
}
