package su.vistar.monitoring.service;

import su.vistar.monitoring.dto.CarDto;
import su.vistar.monitoring.dto.PageDto;
import su.vistar.monitoring.entities.Car;

import java.util.List;

public interface CarService {
    Car getCarById(Long id);

    List<Car> getCars();
    List<Car> getCarsPageable(PageDto pageDto);
    Car addCar(CarDto car);

    Car updateCar(CarDto car);

    void deleteById(Long id);
}
