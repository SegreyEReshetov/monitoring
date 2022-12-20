package su.vistar.monitoring.serviceImpl;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import su.vistar.monitoring.dto.CarModelDto;
import su.vistar.monitoring.dto.PageDto;
import su.vistar.monitoring.entities.CarModel;
import su.vistar.monitoring.exception.ResourceAlreadyExistsException;
import su.vistar.monitoring.exception.ResourceNotFoundException;
import su.vistar.monitoring.repository.CarModelRepository;
import su.vistar.monitoring.repository.EngineRepository;
import su.vistar.monitoring.repository.FuelTypeRepository;
import su.vistar.monitoring.service.CarModelService;

import java.util.List;

@Service
@AllArgsConstructor
public class CarModelServiceImpl implements CarModelService {

    CarModelRepository carModelRepository;
    EngineRepository engineRepository;
    FuelTypeRepository fuelTypeRepository;
    @Override
    public CarModel getCarModelById(Long id) {
        return carModelRepository.findByCarModelId(id)
                .orElseThrow(()->new ResourceNotFoundException("Тип топлива с id = " + id + " не существует"));
    }

    @Override
    public List<CarModel> getCarModels() {
        return carModelRepository.findAll();
    }

    @Override
    public List<CarModel> getCarModelsPageable(PageDto pageDto) {
        return carModelRepository.findAll(pageDto.getPageable());
    }

    @Override
    public CarModel addCarModel(CarModelDto carModelDto, Long engineId, Long fuelTypeId) {
        if (carModelRepository.existsCarModelByCarModelId(carModelDto.getCarModelId())) {
            throw new ResourceAlreadyExistsException("Модель с таким id уже существует");
        }
        CarModel carModel = new CarModel();
        carModel.setCarModelId(carModelDto.getCarModelId());
        initCarModelFromDto(carModel, carModelDto, engineId, fuelTypeId);

        carModelRepository.save(carModel);
        return carModel;
    }

    @Override
    public CarModel updateCarModel(CarModelDto carModelDto, Long engineId, Long fuelTypeId) {
        CarModel updatedCarModel = carModelRepository.findByCarModelId(carModelDto.getCarModelId())
                .orElseThrow(()->new ResourceNotFoundException("Тип топлива с id = " + carModelDto.getCarModelId() + " не существует"));
        initCarModelFromDto(updatedCarModel, carModelDto, engineId, fuelTypeId);

        carModelRepository.save(updatedCarModel);
        return updatedCarModel;
    }

    @Override
    public void deleteById(Long id) {
        carModelRepository.deleteById(id);
    }

    private void initCarModelFromDto(CarModel carModel, CarModelDto carModelDto, Long engineId, Long fuelTypeId) {
        carModel.setFuelVolume(carModelDto.getFuelVolume());
        carModel.setMass(carModelDto.getMass());
        carModel.setWheelRadius(carModelDto.getWheelRadius());
        carModel.setMaxCarriedMass(carModelDto.getMaxCarriedMass());
        carModel.setModifiedDate(carModelDto.getModifiedDate());
        carModel.setEngine(engineRepository.findById(engineId).get());
        carModel.setFuelType(fuelTypeRepository.findByFuelTypeId(fuelTypeId).get());
    }
}
