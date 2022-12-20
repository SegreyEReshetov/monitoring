package su.vistar.monitoring.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import su.vistar.monitoring.entities.RoadPoint;
import su.vistar.monitoring.exception.ResourceAlreadyExistsException;
import su.vistar.monitoring.exception.ResourceNotFoundException;
import su.vistar.monitoring.repository.RoadPointRepository;
import su.vistar.monitoring.service.RoadPointService;

import java.util.List;

@Service
@AllArgsConstructor
public class RoadPointServiceImpl implements RoadPointService {

    RoadPointRepository roadPointRepository;

    @Override
    public RoadPoint getRoadPointById(Long id) {
        return roadPointRepository.findByRoadPointId(id)
                .orElseThrow(()->new ResourceNotFoundException("Точка на дороге с id = " + id + " не существует"));
    }

    @Override
    public List<RoadPoint> getRoadPoints() {
        return roadPointRepository.findAll();
    }

    @Override
    public RoadPoint addRoadPoint(RoadPoint roadPoint) {
        if(roadPointRepository.existsRoadPointByRoadPointId(roadPoint.getRoadPointId())) {
            throw new ResourceAlreadyExistsException("Точки на дороге с таким id уже существует");
        }
        roadPointRepository.save(roadPoint);
        return roadPoint;
    }

    @Override
    public RoadPoint updateRoadPoint(Long id, RoadPoint roadPoint) {
        RoadPoint updatedRoadPoint = roadPointRepository.findByRoadPointId(id)
                .orElseThrow(()->new ResourceNotFoundException("Точка на дороге с id = " + id + " не существует"));


        updatedRoadPoint.setPoint(roadPoint.getPoint());
        updatedRoadPoint.setModifiedDate(roadPoint.getModifiedDate());

        roadPointRepository.save(updatedRoadPoint);
        return updatedRoadPoint;
    }

    @Override
    public void deleteById(Long id) {
        roadPointRepository.deleteById(id);
    }
}
