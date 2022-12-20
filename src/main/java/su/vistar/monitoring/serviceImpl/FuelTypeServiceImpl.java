package su.vistar.monitoring.serviceImpl;

import su.vistar.monitoring.entities.FuelType;
import su.vistar.monitoring.exception.ResourceAlreadyExistsException;
import su.vistar.monitoring.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import su.vistar.monitoring.repository.FuelTypeRepository;
import su.vistar.monitoring.service.FuelTypeService;

import java.util.List;


@Service
@AllArgsConstructor
public class FuelTypeServiceImpl implements FuelTypeService {
    private final FuelTypeRepository fuelTypeRepository;


    @Override
    public FuelType getFuelTypeById(Long id) {
        return fuelTypeRepository.findByFuelTypeId(id)
                .orElseThrow(()->new ResourceNotFoundException("Тип топлива с id = " + id + " не существует"));
    }

    @Override
    public List<FuelType> getFuelTypes() {
        return fuelTypeRepository.findAll();
    }

    @Override
    public FuelType addFuelType(FuelType fuelType) {
        if(fuelTypeRepository.existsFuelTypeByFuelName(fuelType.getFuelName())) {
            throw new ResourceAlreadyExistsException("Тип топлива с таким именем уже существует");
        }
        fuelTypeRepository.save(fuelType);
        return fuelType;
    }

    @Override
    public FuelType updateFuelType(Long id, FuelType fuelType) {
        FuelType updatedFuelType = fuelTypeRepository.findByFuelTypeId(id)
                .orElseThrow(()->new ResourceNotFoundException("Тип топлива с id = " + id + " не существует"));

        updatedFuelType.setFuelName(fuelType.getFuelName());
        updatedFuelType.setModels(fuelType.getModels());
        fuelTypeRepository.save(updatedFuelType);
        return updatedFuelType;
    }

    @Override
    public void deleteById(Long id) {
        fuelTypeRepository.deleteById(id);
    }

}
