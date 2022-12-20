package su.vistar.monitoring.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import su.vistar.monitoring.entities.ControlPoint;
import su.vistar.monitoring.exception.ResourceAlreadyExistsException;
import su.vistar.monitoring.exception.ResourceNotFoundException;
import su.vistar.monitoring.repository.ControlPointRepository;
import su.vistar.monitoring.service.ControlPointService;

import java.util.List;

@Service
@AllArgsConstructor
public class ControlPointServiceImpl implements ControlPointService {

    ControlPointRepository controlPointRepository;

    @Override
    public ControlPoint getControlPointById(Long id) {
        return controlPointRepository.findByControlPointId(id)
                .orElseThrow(()->new ResourceNotFoundException("Контрольная точка с id = " + id + " не существует"));
    }

    @Override
    public List<ControlPoint> getControlPoints() {
        return controlPointRepository.findAll();
    }

    @Override
    public ControlPoint addControlPoint(ControlPoint controlPoint) {
        if(controlPointRepository.existsControlPointByControlPointId(controlPoint.getControlPointId())) {
            throw new ResourceAlreadyExistsException("Контрольная точка с таким id уже существует");
        }
        controlPointRepository.save(controlPoint);
        return controlPoint;
    }

    @Override
    public ControlPoint updateControlPoint(Long id, ControlPoint controlPoint) {
        ControlPoint updatedControlPoint = controlPointRepository.findByControlPointId(id)
                .orElseThrow(()->new ResourceNotFoundException("Контрольная точка с id = " + id + " не существует"));


        updatedControlPoint.setPointNW(controlPoint.getPointNW());
        updatedControlPoint.setPointSE(controlPoint.getPointSE());
        updatedControlPoint.setModifiedDate(controlPoint.getModifiedDate());

        controlPointRepository.save(updatedControlPoint);
        return updatedControlPoint;
    }

    @Override
    public void deleteById(Long id) {
        controlPointRepository.deleteById(id);
    }

}
