package su.vistar.monitoring.repository;

import su.vistar.monitoring.entities.ControlPointOnRoute;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ControlPointOnRouteRepository extends CrudRepository<ControlPointOnRoute, Long> {
    Optional<ControlPointOnRoute> findByControlPointOnRouteId(Long id);

    List<ControlPointOnRoute> findAll();

    boolean existsControlPointOnRouteByControlPointOnRouteId(Long id);
}
