package su.vistar.monitoring.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.geolatte.geom.Geometry;

import java.util.Calendar;
import java.util.List;

@Entity
@Data
public class PlanRoute {
    @Id
    private Long planRouteId;

    private Geometry pointStart;

    private Geometry pointFinish;

    private Calendar createdDate;

    private Calendar modifiedDate;

    @ManyToOne
    private Car car;

    @OneToMany(cascade = CascadeType.ALL)
    private List<FactRoute> factRoutes;

}
