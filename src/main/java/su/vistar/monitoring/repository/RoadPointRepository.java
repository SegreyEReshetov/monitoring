package su.vistar.monitoring.repository;

import su.vistar.monitoring.entities.RoadPoint;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoadPointRepository extends CrudRepository<RoadPoint, Long> {
    Optional<RoadPoint> findByRoadPointId(Long id);

    List<RoadPoint> findAll();

    boolean existsRoadPointByRoadPointId(Long id);
}
