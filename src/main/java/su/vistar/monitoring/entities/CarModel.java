package su.vistar.monitoring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Calendar;

@Entity
@Data
public class CarModel {
    @Id
    private Long carModelId;

    private Integer fuelVolume;
    private Integer mass;
    private Integer wheelRadius;
    private Integer fuelMileage;
    private Integer maxCarriedMass;
    private Calendar createdDate;

    private Calendar modifiedDate;

    @ManyToOne
    private Engine engine;
    @ManyToOne
    private FuelType fuelType;
}
