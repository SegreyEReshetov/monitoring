package su.vistar.monitoring.service;

import su.vistar.monitoring.entities.ControlPointOnRoute;

import java.util.List;

public interface ControlPointOnRouteService {

    ControlPointOnRoute getControlPointOnRouteById(Long id);

    List<ControlPointOnRoute> getControlPointOnRoutes();

    ControlPointOnRoute addControlPointOnRoute(ControlPointOnRoute controlPointOnRoute);

    ControlPointOnRoute updateControlPointOnRoute(Long id, ControlPointOnRoute controlPointOnRoute);

    void deleteById(Long id);
}
