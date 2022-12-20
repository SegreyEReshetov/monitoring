package su.vistar.monitoring.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;

@Entity
@Data
public class Measurement {

    @Id
    private Long measurementId;

    private Calendar dateMeasuring;

    private Integer speed;

    private Integer engineSpeed;

    private Double gasPedalPosition;

    private Double brakePedalPosition;

    private Double clutchPedalPosition;

    private Integer numberOfSpeed;

    private Integer fluidLevel;

    private Calendar createdDate;

    private Calendar modifiedDate;

    @ManyToOne
    private Car car;

}
