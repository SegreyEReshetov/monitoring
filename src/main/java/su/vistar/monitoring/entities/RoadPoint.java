package su.vistar.monitoring.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.geolatte.geom.Geometry;

import java.util.Calendar;
import java.util.List;

@Entity
@Data
public class RoadPoint {
    @Id
    private Long roadPointId;

    private Geometry point;


    private Calendar createdDate;

    private Calendar modifiedDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ControlPointOnRoute> controlPointOnRouteList;
}
