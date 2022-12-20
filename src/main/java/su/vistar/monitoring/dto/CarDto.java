package su.vistar.monitoring.dto;

import lombok.Data;
import su.vistar.monitoring.entities.Car;

import java.util.Calendar;

@Data
public class CarDto {
    private Long carId;

    private Calendar registrationDate;
    private Calendar createdDate;
    private Calendar modifiedDate;

    private Long carModelId;

    public CarDto(Car c) {
        this.carId = c.getCarId();
        this.registrationDate = c.getRegistrationDate();
        this.createdDate = c.getCreatedDate();
        this.modifiedDate = c.getModifiedDate();
        this.carModelId = c.getCarModel().getCarModelId();
    }
}
