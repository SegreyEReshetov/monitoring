package su.vistar.monitoring.service;

import su.vistar.monitoring.entities.Measurement;

import java.util.List;

public interface MeasurementService {
    Measurement getMeasurementById(Long id);

    List<Measurement> getMeasurements();

    Measurement addMeasurement(Measurement measurement);

    Measurement updateMeasurement(Long id, Measurement measurement);

    void deleteById(Long id);
}
