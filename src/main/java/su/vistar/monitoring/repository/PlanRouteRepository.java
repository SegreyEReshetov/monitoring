package su.vistar.monitoring.repository;

import su.vistar.monitoring.entities.PlanRoute;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PlanRouteRepository extends CrudRepository<PlanRoute, Long> {
    Optional<PlanRoute> findByPlanRouteId(Long id);

    List<PlanRoute> findAll();

    boolean existsPlanRouteByPlanRouteId(Long id);
}
