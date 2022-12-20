package su.vistar.monitoring.repository;

import su.vistar.monitoring.entities.Schedule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    Optional<Schedule> findByScheduleId(Long id);

    List<Schedule> findAll();

    boolean existsScheduleByScheduleId(Long id);
}
