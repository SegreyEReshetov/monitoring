package su.vistar.monitoring.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Entity
@Data
@Table(name = "fuel_type")
public class FuelType {

    @Id
    @Column(name = "id")
    private Long fuelTypeId;

    @Column(name = "name")
    private String fuelName;

    @Column(name = "created_date")
    private Calendar createdDate;

    @Column(name = "modified_date")
    private Calendar modifiedDate;

    @OneToMany(cascade =  CascadeType.ALL)
    private List<CarModel> models;
}
