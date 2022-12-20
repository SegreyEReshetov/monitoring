package su.vistar.monitoring.service;

import su.vistar.monitoring.entities.Schedule;

import java.util.List;

public interface ScheduleService {

    Schedule getScheduleById(Long id);

    List<Schedule> getSchedules();

    Schedule addSchedule(Schedule schedule);

    Schedule updateSchedule(Long id, Schedule schedule);

    void deleteById(Long id);
}
