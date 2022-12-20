package su.vistar.monitoring.repository;

import su.vistar.monitoring.entities.FuelType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FuelTypeRepository extends CrudRepository<FuelType, Long> {
   // List<FuelType> findByFuelTypeId(Long id);

    Optional<FuelType> findByFuelTypeId(Long id);

    List<FuelType> findByFuelName(String name);

    List<FuelType> findAll();

    boolean existsFuelTypeByFuelName(String name);
}
