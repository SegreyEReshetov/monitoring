package su.vistar.monitoring.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.geolatte.geom.Geometry;

import java.util.Calendar;
import java.util.List;

@Entity
@Data
public class FactRoute {
    @Id
    private Long factRouteId;

    private Geometry PointStart;

    private Geometry PointEnd;

    private Calendar createdDate;

    private Calendar modifiedDate;

    @ManyToOne
    private PlanRoute planRoute;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ControlPointOnRoute> ControlPointOnRoutes;


}
