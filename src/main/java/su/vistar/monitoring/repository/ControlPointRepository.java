package su.vistar.monitoring.repository;

import su.vistar.monitoring.entities.ControlPoint;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ControlPointRepository extends CrudRepository<ControlPoint, Long> {
    Optional<ControlPoint> findByControlPointId(Long id);

    List<ControlPoint> findAll();

    boolean existsControlPointByControlPointId(Long id);
}
