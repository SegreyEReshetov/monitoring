package su.vistar.monitoring.service;

import su.vistar.monitoring.entities.FuelType;

import java.util.List;

public interface FuelTypeService {

    FuelType getFuelTypeById(Long id);

    List<FuelType> getFuelTypes();

    FuelType addFuelType(FuelType fuelType);

    FuelType updateFuelType(Long id, FuelType fuelType);

    void deleteById(Long id);
}
