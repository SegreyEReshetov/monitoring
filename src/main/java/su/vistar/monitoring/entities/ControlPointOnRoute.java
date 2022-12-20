package su.vistar.monitoring.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Calendar;

@Entity
@Data
public class ControlPointOnRoute {

    @Id
    private Long controlPointOnRouteId;

    @ManyToOne
    private ControlPoint controlPoint;

    @ManyToOne
    private FactRoute factRoute;

    @OneToOne
    private Schedule schedule;

    private Calendar passageTime;

    private Calendar createdDate;

    private Calendar modifiedDate;

}
