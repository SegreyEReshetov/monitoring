package su.vistar.monitoring.dto;

import lombok.Data;
import su.vistar.monitoring.entities.Owner;

import java.util.Calendar;

@Data
public class OwnerDto {
    private Long ownerId;

    private String surname;
    private String name;
    private String patronymic;
    private Calendar birthDate;
    private Calendar createdDate;
    private Calendar modifiedDate;

    private Long carId;
    public OwnerDto(Owner o) {
        this.ownerId = o.getOwnerId();
        this.surname = o.getSurname();
        this.name = o.getName();
        this.patronymic = o.getPatronymic();
        this.birthDate = o.getBirthDate();
        this.createdDate = o.getCreatedDate();
        this.modifiedDate =o.getModifiedDate();
        this.carId = o.getCar().getCarId();
    }
}
