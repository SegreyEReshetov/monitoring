package su.vistar.monitoring.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import su.vistar.monitoring.dto.OwnerDto;
import su.vistar.monitoring.dto.PageDto;
import su.vistar.monitoring.entities.Car;
import su.vistar.monitoring.entities.Owner;
import su.vistar.monitoring.exception.ResourceAlreadyExistsException;
import su.vistar.monitoring.exception.ResourceNotFoundException;
import su.vistar.monitoring.repository.CarRepository;
import su.vistar.monitoring.repository.OwnerRepository;
import su.vistar.monitoring.service.OwnerService;

import java.util.List;

@Service
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    OwnerRepository ownerRepository;
    CarRepository carRepository;
    @Override
    public Owner getOwnerById(Long id) {
        return ownerRepository.findByOwnerId(id)
                .orElseThrow(()->new ResourceNotFoundException("Владельца с id = " + id + " не существует"));
    }

    @Override
    public List<Owner> getOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public List<Owner> getOwnersPageable(PageDto page) {
        return ownerRepository.findAll(page.getPageable());
    }

    @Override
    public Owner addOwner(OwnerDto ownerDto, Long carId) {
        if (ownerRepository.existsOwnerByOwnerId(ownerDto.getOwnerId())) {
            throw new ResourceAlreadyExistsException("Владелец с таким id уже существует");
        }
        Car car = carRepository.findByCarId(ownerDto.getCarId())
                .orElseThrow(()->new ResourceNotFoundException("Машина с id = " + ownerDto.getCarId() + " не существует"));
        Owner owner = new Owner();
        owner.setOwnerId(ownerDto.getOwnerId());
        initOwnerFromDto(owner, ownerDto, car);
        ownerRepository.save(owner);
        return owner;
    }

    @Override
    public Owner updateOwner(OwnerDto ownerDto, Long carId) {
        Owner updatedOwner = ownerRepository.findByOwnerId(ownerDto.getOwnerId())
                .orElseThrow(()->new ResourceNotFoundException("Владельца с id = " + ownerDto.getOwnerId() + " не существует"));
        Car car = carRepository.findByCarId(ownerDto.getCarId())
                .orElseThrow(()->new ResourceNotFoundException("Машина с id = " + ownerDto.getCarId() + " не существует"));

        initOwnerFromDto(updatedOwner, ownerDto, car);

        ownerRepository.save(updatedOwner);
        return updatedOwner;
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    private void initOwnerFromDto(Owner owner, OwnerDto dto, Car car) {
        owner.setBirthDate(dto.getBirthDate());
        owner.setName(dto.getName());
        owner.setPatronymic(dto.getPatronymic());
        owner.setSurname(dto.getSurname());
        owner.setModifiedDate(dto.getModifiedDate());

        owner.setCar(car);
    }
}
