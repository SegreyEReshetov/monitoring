package su.vistar.monitoring.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import su.vistar.monitoring.entities.Car;
import su.vistar.monitoring.entities.PlanRoute;
import su.vistar.monitoring.exception.ResourceAlreadyExistsException;
import su.vistar.monitoring.exception.ResourceNotFoundException;
import su.vistar.monitoring.repository.CarRepository;
import su.vistar.monitoring.repository.PlanRouteRepository;
import su.vistar.monitoring.service.PlanRouteService;

import java.util.List;

@Service
@AllArgsConstructor
public class PlanRouteServiceImpl implements PlanRouteService {

    PlanRouteRepository planRouteRepository;
    CarRepository carRepository;
    @Override
    public PlanRoute getPlanRouteById(Long id) {
        return planRouteRepository.findByPlanRouteId(id)
                .orElseThrow(()->new ResourceNotFoundException("Тип топлива с id = " + id + " не существует"));
    }

    @Override
    public List<PlanRoute> getPlanRoutes() {
        return planRouteRepository.findAll();
    }

    @Override
    public PlanRoute addPlanRoute(PlanRoute planRoute) {
        if(planRouteRepository.existsPlanRouteByPlanRouteId(planRoute.getPlanRouteId())) {
            throw new ResourceAlreadyExistsException("Модель с таким id уже существует");
        }
        planRouteRepository.save(planRoute);
        return planRoute;
    }

    @Override
    public PlanRoute updatePlanRoute(Long id, PlanRoute planRoute) {
        PlanRoute updatedPlanRoute = planRouteRepository.findByPlanRouteId(id)
                .orElseThrow(()->new ResourceNotFoundException("Тип топлива с id = " + id + " не существует"));
        Car car = carRepository.findByCarId(planRoute.getCar().getCarId())
                .orElseThrow(()->new ResourceNotFoundException("Машина с id = " + planRoute.getCar().getCarId() + " не существует"));


        updatedPlanRoute.setPointStart(planRoute.getPointStart());
        updatedPlanRoute.setPointFinish(planRoute.getPointFinish());
        updatedPlanRoute.setCar(car);
        updatedPlanRoute.setModifiedDate(planRoute.getModifiedDate());


        planRouteRepository.save(updatedPlanRoute);
        return updatedPlanRoute;
    }

    @Override
    public void deleteById(Long id) {
        planRouteRepository.deleteById(id);
    }

}
