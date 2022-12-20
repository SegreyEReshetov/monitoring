package su.vistar.monitoring.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import su.vistar.monitoring.entities.Car;
import su.vistar.monitoring.entities.Measurement;
import su.vistar.monitoring.exception.ResourceAlreadyExistsException;
import su.vistar.monitoring.exception.ResourceNotFoundException;
import su.vistar.monitoring.repository.CarRepository;
import su.vistar.monitoring.repository.MeasurementRepository;
import su.vistar.monitoring.service.MeasurementService;

import java.util.List;

@Service
@AllArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {

    MeasurementRepository measurementRepository;
    CarRepository carRepository;

    @Override
    public Measurement getMeasurementById(Long id) {
        return measurementRepository.findByMeasurementId(id)
                .orElseThrow(()->new ResourceNotFoundException("Измерение с id = " + id + " не существует"));
    }

    @Override
    public List<Measurement> getMeasurements() {
        return measurementRepository.findAll();
    }

    @Override
    public Measurement addMeasurement(Measurement measurement) {
        if(measurementRepository.existsMeasurementByMeasurementId(measurement.getMeasurementId())) {
            throw new ResourceAlreadyExistsException("Измерение с таким id уже существует");
        }
        measurementRepository.save(measurement);
        return measurement;
    }

    @Override
    public Measurement updateMeasurement(Long id, Measurement measurement) {
        Measurement updatedMeasurement = measurementRepository.findByMeasurementId(id)
                .orElseThrow(()->new ResourceNotFoundException("Измерение с id = " + id + " не существует"));
        Car car = carRepository.findByCarId(measurement.getCar().getCarId())
                .orElseThrow(()->new ResourceNotFoundException("Машина с id = " + id + " не существует"));

        updatedMeasurement.setDateMeasuring(measurement.getDateMeasuring());
        updatedMeasurement.setCar(car);
        updatedMeasurement.setSpeed(measurement.getSpeed());
        updatedMeasurement.setEngineSpeed(measurement.getEngineSpeed());
        updatedMeasurement.setGasPedalPosition(measurement.getGasPedalPosition());
        updatedMeasurement.setBrakePedalPosition(measurement.getBrakePedalPosition());
        updatedMeasurement.setClutchPedalPosition(measurement.getClutchPedalPosition());
        updatedMeasurement.setNumberOfSpeed(measurement.getNumberOfSpeed());
        updatedMeasurement.setFluidLevel(measurement.getFluidLevel());
        updatedMeasurement.setModifiedDate(measurement.getModifiedDate());


        measurementRepository.save(updatedMeasurement);
        return updatedMeasurement;
    }

    @Override
    public void deleteById(Long id) {
        measurementRepository.deleteById(id);
    }
}
