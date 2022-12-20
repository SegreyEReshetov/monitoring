package su.vistar.monitoring.service;

import su.vistar.monitoring.dto.EngineDto;
import su.vistar.monitoring.dto.PageDto;
import su.vistar.monitoring.entities.Engine;

import java.util.List;

public interface EngineService {
    Engine getEngineById(Long id);

    List<Engine> getEngines();
    List<Engine> getEnginesPageable(PageDto page);
    Engine addEngine(EngineDto engine);

    Engine updateEngine(EngineDto engine);

    void deleteById(Long id);
}
