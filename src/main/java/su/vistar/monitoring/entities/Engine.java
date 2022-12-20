package su.vistar.monitoring.entities;

import jakarta.persistence.*;
import lombok.Data;

import javax.persistence.Column;
import java.util.Calendar;
import java.util.List;

@Entity
@Data
@Table(name="engine")
public class Engine {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    private String name;
    @Column(name="cylinder_amount")
    private Integer cylinderAmount;
    @Column(name="created_date")
    private Calendar createdDate;
    @Column(name="modified_date")
    private Calendar modifiedDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CarModel> models;
}
