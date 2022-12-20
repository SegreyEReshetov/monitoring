package su.vistar.monitoring.dto;

import lombok.Data;
import su.vistar.monitoring.entities.CarModel;

import java.util.Calendar;

@Data
public class CarModelDto {
    private Long carModelId;

    private Integer fuelVolume;
    private Integer mass;
    private Integer wheelRadius;
    private Integer fuelMileage;
    private Integer maxCarriedMass;
    private Calendar createdDate;
    private Calendar modifiedDate;

    public CarModelDto(CarModel c) {
        this.carModelId = c.getCarModelId();
        this.fuelVolume = c.getFuelVolume();
        this.mass = c.getMass();
        this.wheelRadius = c.getWheelRadius();
        this.fuelMileage = c.getFuelMileage();
        this.maxCarriedMass = c.getMaxCarriedMass();
        this.createdDate = c.getCreatedDate();
        this.modifiedDate = c.getModifiedDate();
    }
}
