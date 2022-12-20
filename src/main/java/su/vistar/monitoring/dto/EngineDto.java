package su.vistar.monitoring.dto;

import lombok.Data;
import su.vistar.monitoring.entities.Engine;

import java.util.Calendar;

@Data
public class EngineDto {
    private Long engineId;

    private String name;
    private Integer cylinderAmount;
    private Calendar createdDate;
    private Calendar modifiedDate;

    public EngineDto(Engine e) {
        this.engineId = e.getId();
        this.name = e.getName();
        this.cylinderAmount = e.getCylinderAmount();
        this.createdDate = e.getCreatedDate();
        this.modifiedDate = e.getModifiedDate();
    }
}
