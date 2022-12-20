package su.vistar.monitoring.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import su.vistar.monitoring.dto.CarDto;
import su.vistar.monitoring.dto.PageDto;
import su.vistar.monitoring.entities.Car;
import su.vistar.monitoring.entities.CarModel;
import su.vistar.monitoring.exception.ResourceAlreadyExistsException;
import su.vistar.monitoring.exception.ResourceNotFoundException;
import su.vistar.monitoring.repository.CarModelRepository;
import su.vistar.monitoring.repository.CarRepository;
import su.vistar.monitoring.service.CarService;

import java.util.List;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    CarRepository carRepository;
    CarModelRepository carModelRepository;
    @Override
    public Car getCarById(Long id) {
        return carRepository.findByCarId(id)
                .orElseThrow(()->new ResourceNotFoundException("Машина с id = " + id + " не существует"));
    }

    @Override
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getCarsPageable(PageDto pageDto) {
        return carRepository.findAll(pageDto.getPageable());
    }

    @Override
    public Car addCar(CarDto carDto) {
        if (carRepository.existsCarByCarId(carDto.getCarId())) {
            throw new ResourceAlreadyExistsException("Машина с таким id уже существует");
        }
        Car car = new Car();
        CarModel carModel = carModelRepository.findByCarModelId(carDto.getCarModelId())
                .orElseThrow(() -> new ResourceNotFoundException("Модели с id = " + carDto.getCarModelId() + " не существует"));
        car.setCarId(carDto.getCarId());

        initCarFromDto(car, carDto, carModel);
        carRepository.save(car);
        return car;
    }

    @Override
    public Car updateCar(CarDto carDto) {
        Car updatedCar = carRepository.findByCarId(carDto.getCarId())
                .orElseThrow(()->new ResourceNotFoundException("Машина с id = " + carDto.getCarId() + " не существует"));
        CarModel updatedModel = carModelRepository.findByCarModelId(carDto.getCarModelId())
                .orElseThrow(()->new ResourceNotFoundException("Модели с id = " + carDto.getCarModelId() + " не существует"));

        initCarFromDto(updatedCar, carDto, updatedModel);
        carRepository.save(updatedCar);
        return updatedCar;
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    private void initCarFromDto(Car car, CarDto carDto, CarModel carModel) {
        car.setRegistrationDate(carDto.getRegistrationDate());
        car.setCarModel(carModel);
        car.setModifiedDate(carDto.getModifiedDate());
    }
}
