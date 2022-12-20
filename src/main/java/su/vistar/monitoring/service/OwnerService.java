package su.vistar.monitoring.service;

import su.vistar.monitoring.dto.OwnerDto;
import su.vistar.monitoring.dto.PageDto;
import su.vistar.monitoring.entities.Owner;

import java.util.List;

public interface OwnerService {
    Owner getOwnerById(Long id);

    List<Owner> getOwners();
    List<Owner> getOwnersPageable(PageDto page);

    Owner addOwner(OwnerDto owner, Long carId);

    Owner updateOwner(OwnerDto owner, Long carId);

    void deleteById(Long id);
}
