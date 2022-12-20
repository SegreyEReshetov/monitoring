package su.vistar.monitoring.service;

import su.vistar.monitoring.entities.ControlPoint;

import java.util.List;

public interface ControlPointService {

    ControlPoint getControlPointById(Long id);

    List<ControlPoint> getControlPoints();

    ControlPoint addControlPoint(ControlPoint controlPoint);

    ControlPoint updateControlPoint(Long id, ControlPoint controlPoint);

    void deleteById(Long id);
}
