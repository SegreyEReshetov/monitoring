package su.vistar.monitoring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.geolatte.geom.Geometry;

import java.util.Calendar;
import java.util.List;

@Entity
@Data
public class ControlPoint {
    @Id
    private Long controlPointId;

    private Geometry PointNW;

    private Geometry PointSE;

    private Calendar createdDate;

    private Calendar modifiedDate;

    @OneToMany
    private List<ControlPointOnRoute> controlPointsOnRoute;
}
