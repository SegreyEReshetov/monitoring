package su.vistar.monitoring.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import su.vistar.monitoring.dto.OwnerDto;
import su.vistar.monitoring.dto.PageDto;
import su.vistar.monitoring.service.OwnerService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService service;

    @GetMapping("/size={pageSize}/page={pageNum}")
    public ResponseEntity<Page<OwnerDto>> getAllOwnersPageable(@PathVariable int pageSize,
                                                               @PathVariable int pageNum) {
        Page<OwnerDto> page = new PageImpl<>(service.getOwnersPageable(new PageDto(pageSize, pageNum))
                .stream()
                .map(OwnerDto::new)
                .collect(Collectors.toList()));
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable Long id) {
        return new ResponseEntity<>(new OwnerDto(service.getOwnerById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OwnerDto>> getOwners(){
        return new ResponseEntity<>(service.getOwners()
                .stream()
                .map(OwnerDto::new)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/car={carId}")
    public ResponseEntity<OwnerDto> addOwner(@RequestBody OwnerDto ownerDto, @PathVariable Long carId){
        return new ResponseEntity<>(new OwnerDto(service.addOwner(ownerDto, carId)), HttpStatus.CREATED);
    }

    @PutMapping("/car={carId}")
    public ResponseEntity<OwnerDto> updateOwner(@RequestBody OwnerDto ownerDto, @PathVariable Long carId){
        return new ResponseEntity<>(new OwnerDto(service.updateOwner(ownerDto, carId)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }
}
