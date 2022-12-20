package su.vistar.monitoring.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import su.vistar.monitoring.entities.ControlPoint;
import su.vistar.monitoring.entities.ControlPointOnRoute;
import su.vistar.monitoring.entities.FactRoute;
import su.vistar.monitoring.entities.Schedule;
import su.vistar.monitoring.exception.ResourceAlreadyExistsException;
import su.vistar.monitoring.exception.ResourceNotFoundException;
import su.vistar.monitoring.repository.ControlPointOnRouteRepository;
import su.vistar.monitoring.repository.ControlPointRepository;
import su.vistar.monitoring.repository.FactRouteRepository;
import su.vistar.monitoring.repository.ScheduleRepository;
import su.vistar.monitoring.service.ControlPointOnRouteService;

import java.util.List;

@Service
@AllArgsConstructor
public class ControlPointOnRouteServiceImpl implements ControlPointOnRouteService {

    ControlPointOnRouteRepository controlPointOnRouteRepository;
    ControlPointRepository controlPointRepository;
    FactRouteRepository factRouteRepository;
    ScheduleRepository scheduleRepository;

    @Override
    public ControlPointOnRoute getControlPointOnRouteById(Long id) {
        return controlPointOnRouteRepository.findByControlPointOnRouteId(id)
                .orElseThrow(()->new ResourceNotFoundException("Контрольной точки с id = " + id + " не существует"));
    }

    @Override
    public List<ControlPointOnRoute> getControlPointOnRoutes() {
        return controlPointOnRouteRepository.findAll();
    }

    @Override
    public ControlPointOnRoute addControlPointOnRoute(ControlPointOnRoute controlPointOnRoute) {
        if(controlPointOnRouteRepository.existsControlPointOnRouteByControlPointOnRouteId(controlPointOnRoute.getControlPointOnRouteId())) {
            throw new ResourceAlreadyExistsException("Контрольная точка с таким id уже существует");
        }
        controlPointOnRouteRepository.save(controlPointOnRoute);
        return controlPointOnRoute;
    }

    @Override
    public ControlPointOnRoute updateControlPointOnRoute(Long id, ControlPointOnRoute controlPointOnRoute) {
        ControlPointOnRoute updatedControlPointOnRoute = controlPointOnRouteRepository.findByControlPointOnRouteId(id)
                .orElseThrow(()->new ResourceNotFoundException("Контрольная точка с id = " + id + " не существует"));
        ControlPoint controlPoint = controlPointRepository.findByControlPointId(controlPointOnRoute.
                getControlPoint().getControlPointId()).
                    orElseThrow(()->new ResourceNotFoundException("Точка с id = " + id + " не существует"));
        FactRoute factRoute = factRouteRepository.findByFactRouteId(controlPointOnRoute.
                getFactRoute().getFactRouteId()).
                orElseThrow(()->new ResourceNotFoundException("Фактический маршрут с id = " + id + " не существует"));
        Schedule schedule = scheduleRepository.findByScheduleId(controlPointOnRoute.getSchedule().getScheduleId()).
                orElseThrow(()->new ResourceNotFoundException("Расписание с id = " + id + " не существует"));


        updatedControlPointOnRoute.setControlPoint(controlPoint);
        updatedControlPointOnRoute.setFactRoute(factRoute);
        updatedControlPointOnRoute.setSchedule(schedule);
        updatedControlPointOnRoute.setPassageTime(controlPointOnRoute.getPassageTime());
        updatedControlPointOnRoute.setModifiedDate(controlPointOnRoute.getModifiedDate());


        controlPointOnRouteRepository.save(updatedControlPointOnRoute);
        return updatedControlPointOnRoute;
    }

    @Override
    public void deleteById(Long id) {
        controlPointOnRouteRepository.deleteById(id);
    }


}
