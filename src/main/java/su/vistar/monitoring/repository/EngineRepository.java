package su.vistar.monitoring.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import su.vistar.monitoring.entities.Engine;

import java.util.List;
import java.util.Optional;

public interface EngineRepository extends CrudRepository<Engine, Long> {
    Optional<Engine> findById(Long id);

    List<Engine> findAll();
    List<Engine> findAll(Pageable pageable);

    boolean existsEngineById(Long id);
}
