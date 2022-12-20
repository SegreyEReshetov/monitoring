package su.vistar.monitoring.repository;

import su.vistar.monitoring.entities.FactRoute;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FactRouteRepository extends CrudRepository<FactRoute, Long> {
    Optional<FactRoute> findByFactRouteId(Long id);

    List<FactRoute> findAll();

    boolean existsFactRouteByFactRouteId(Long id);
}
