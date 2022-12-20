package su.vistar.monitoring.service;

import su.vistar.monitoring.entities.FactRoute;

import java.util.List;

public interface FactRouteService {
    FactRoute getFactRouteById(Long id);

    List<FactRoute> getFactRoutes();

    FactRoute addFactRoute(FactRoute factRoute);

    FactRoute updateFactRoute(Long id, FactRoute factRoute);

    void deleteById(Long id);
}
