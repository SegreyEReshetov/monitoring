package su.vistar.monitoring.service;

import su.vistar.monitoring.dto.CarModelDto;
import su.vistar.monitoring.dto.PageDto;
import su.vistar.monitoring.entities.CarModel;

import java.util.List;

public interface CarModelService {

    CarModel getCarModelById(Long id);

    List<CarModel> getCarModels();
    List<CarModel> getCarModelsPageable(PageDto pageDto);

    CarModel addCarModel(CarModelDto carModel, Long engineId, Long fuelTypeId);

    CarModel updateCarModel(CarModelDto carModel, Long engineId, Long fuelTypeId);

    void deleteById(Long id);

}
