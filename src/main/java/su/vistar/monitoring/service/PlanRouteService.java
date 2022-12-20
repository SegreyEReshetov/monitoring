package su.vistar.monitoring.service;

import su.vistar.monitoring.entities.PlanRoute;

import java.util.List;

public interface PlanRouteService {
    PlanRoute getPlanRouteById(Long id);

    List<PlanRoute> getPlanRoutes();

    PlanRoute addPlanRoute(PlanRoute planRoute);

    PlanRoute updatePlanRoute(Long id, PlanRoute planRoute);

    void deleteById(Long id);
}
