package su.vistar.monitoring.service;

import su.vistar.monitoring.entities.RoadPoint;

import java.util.List;

public interface RoadPointService {
    RoadPoint getRoadPointById(Long id);

    List<RoadPoint> getRoadPoints();

    RoadPoint addRoadPoint(RoadPoint roadPoint);

    RoadPoint updateRoadPoint(Long id, RoadPoint roadPoint);

    void deleteById(Long id);
}
