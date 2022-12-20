package su.vistar.monitoring.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import su.vistar.monitoring.dto.EngineDto;
import su.vistar.monitoring.dto.PageDto;
import su.vistar.monitoring.entities.Engine;
import su.vistar.monitoring.exception.ResourceAlreadyExistsException;
import su.vistar.monitoring.exception.ResourceNotFoundException;
import su.vistar.monitoring.repository.EngineRepository;
import su.vistar.monitoring.service.EngineService;

import java.util.List;

@Service
@AllArgsConstructor
public class EngineServiceImpl implements EngineService {

    EngineRepository engineRepository;

    @Override
    public Engine getEngineById(Long id) {
        return engineRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Двигателя с id = " + id + " не существует"));
    }

    @Override
    public List<Engine> getEngines() {
        return engineRepository.findAll();
    }

    @Override
    public List<Engine> getEnginesPageable(PageDto page) {
        return engineRepository.findAll(page.getPageable());
    }

    @Override
    public Engine addEngine(EngineDto engineDto) {
        if (engineRepository.existsEngineById(engineDto.getEngineId())) {
            throw new ResourceAlreadyExistsException("Двигатель с таким id уже существует");
        }
        Engine engine = new Engine();
        engine.setId(engineDto.getEngineId());
        initEngineFromDto(engine, engineDto);
        engineRepository.save(engine);
        return engine;
    }

    @Override
    public Engine updateEngine(EngineDto engineDto) {
        Engine updatedEngine = engineRepository.findById(engineDto.getEngineId())
                .orElseThrow(()->new ResourceNotFoundException("Двигателя с id = " + engineDto.getEngineId() + " не существует"));

        initEngineFromDto(updatedEngine, engineDto);

        engineRepository.save(updatedEngine);
        return updatedEngine;
    }

    @Override
    public void deleteById(Long id) {
        engineRepository.deleteById(id);
    }

    private void initEngineFromDto(Engine engine, EngineDto engineDto) {
        engine.setCylinderAmount(engineDto.getCylinderAmount());
        engine.setName(engineDto.getName());
        engine.setModifiedDate(engineDto.getModifiedDate());
    }
}
