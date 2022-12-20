package su.vistar.monitoring.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.Calendar;

@Entity
@Data
public class Owner {
    @Id
    private Long ownerId;

    private String surname;
    private String name;
    private String patronymic;

    private Calendar birthDate;
    private Calendar createdDate;
    private Calendar modifiedDate;

    @OneToOne (cascade = CascadeType.ALL)
    private Car car;
}
