package su.vistar.monitoring.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import su.vistar.monitoring.entities.ControlPointOnRoute;
import su.vistar.monitoring.entities.Schedule;
import su.vistar.monitoring.exception.ResourceAlreadyExistsException;
import su.vistar.monitoring.exception.ResourceNotFoundException;
import su.vistar.monitoring.repository.ControlPointOnRouteRepository;
import su.vistar.monitoring.repository.ScheduleRepository;
import su.vistar.monitoring.service.ScheduleService;

import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    ScheduleRepository scheduleRepository;
    ControlPointOnRouteRepository controlPointOnRouteRepository;
    @Override
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findByScheduleId(id)
                .orElseThrow(()->new ResourceNotFoundException("Расписание с id = " + id + " не существует"));
    }

    @Override
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule addSchedule(Schedule schedule) {
        if(scheduleRepository.existsScheduleByScheduleId(schedule.getScheduleId())) {
            throw new ResourceAlreadyExistsException("Расписание с таким id уже существует");
        }
        scheduleRepository.save(schedule);
        return schedule;
    }

    @Override
    public Schedule updateSchedule(Long id, Schedule schedule) {
        Schedule updatedSchedule = scheduleRepository.findByScheduleId(id)
                .orElseThrow(()->new ResourceNotFoundException("Расписание с id = " + id + " не существует"));
        ControlPointOnRoute controlPointOnRoute = controlPointOnRouteRepository
                .findByControlPointOnRouteId(schedule.getControlPointOnRoute().getControlPointOnRouteId())
                .orElseThrow(()->new ResourceNotFoundException("Контрольная точка на дороге с id = " +
                                schedule.getControlPointOnRoute().getControlPointOnRouteId() + " не существует"));


        updatedSchedule.setControlPointOnRoute(controlPointOnRoute);
        updatedSchedule.setPlanTimeArrived(schedule.getPlanTimeArrived());
        updatedSchedule.setModifiedDate(schedule.getModifiedDate());


        scheduleRepository.save(updatedSchedule);
        return updatedSchedule;
    }

    @Override
    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }
}
