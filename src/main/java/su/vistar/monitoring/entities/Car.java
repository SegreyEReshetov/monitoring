package su.vistar.monitoring.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Entity
@Data
public class Car {
    @Id
    private Long carId;

    private Calendar registrationDate;

    private Calendar createdDate;

    private Calendar modifiedDate;

    @ManyToOne
    private CarModel carModel;

    @OneToOne(cascade = CascadeType.ALL)
    private Owner owner;

    @OneToMany(cascade =  CascadeType.ALL)
    private List<PlanRoute> planRotes;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Measurement> measurements;

}
