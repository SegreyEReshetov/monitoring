package su.vistar.monitoring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Calendar;

@Entity
@Data
public class Schedule {

    @Id
    private Long scheduleId;

    private Calendar planTimeArrived;

    private Calendar createdDate;

    private Calendar modifiedDate;

    @OneToOne
    private ControlPointOnRoute controlPointOnRoute;
}
