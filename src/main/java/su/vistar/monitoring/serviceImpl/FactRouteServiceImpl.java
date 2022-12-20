package su.vistar.monitoring.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import su.vistar.monitoring.entities.FactRoute;
import su.vistar.monitoring.entities.PlanRoute;
import su.vistar.monitoring.exception.ResourceAlreadyExistsException;
import su.vistar.monitoring.exception.ResourceNotFoundException;
import su.vistar.monitoring.repository.FactRouteRepository;
import su.vistar.monitoring.repository.PlanRouteRepository;
import su.vistar.monitoring.service.FactRouteService;

import java.util.List;

@Service
@AllArgsConstructor
public class FactRouteServiceImpl implements FactRouteService {

    FactRouteRepository factRouteRepository;
    PlanRouteRepository planRouteRepository;

    @Override
    public FactRoute getFactRouteById(Long id) {
        return factRouteRepository.findByFactRouteId(id)
                .orElseThrow(()->new ResourceNotFoundException("Фактический маршрут с id = " + id + " не существует"));
    }

    @Override
    public List<FactRoute> getFactRoutes() {
        return factRouteRepository.findAll();
    }

    @Override
    public FactRoute addFactRoute(FactRoute factRoute) {
        if(factRouteRepository.existsFactRouteByFactRouteId(factRoute.getFactRouteId())) {
            throw new ResourceAlreadyExistsException("Фактический маршрут с таким id уже существует");
        }
        factRouteRepository.save(factRoute);
        return factRoute;
    }

    @Override
    public FactRoute updateFactRoute(Long id, FactRoute factRoute) {
        FactRoute updatedFactRoute = factRouteRepository.findByFactRouteId(id)
                .orElseThrow(()->new ResourceNotFoundException("Фактический маршрут с id = " + id + " не существует"));

        PlanRoute planRoute = planRouteRepository.findByPlanRouteId(factRoute.getPlanRoute().getPlanRouteId())
                .orElseThrow(()->new ResourceNotFoundException("Плановый маршрут с id = " + id + " не существует"));


        updatedFactRoute.setPointStart(factRoute.getPointStart());
        updatedFactRoute.setPointEnd(factRoute.getPointEnd());
        updatedFactRoute.setPlanRoute(planRoute);
        updatedFactRoute.setModifiedDate(factRoute.getModifiedDate());

        factRouteRepository.save(updatedFactRoute);
        return updatedFactRoute;
    }

    @Override
    public void deleteById(Long id) {
        factRouteRepository.deleteById(id);
    }
}
