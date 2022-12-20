package su.vistar.monitoring.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import su.vistar.monitoring.dto.CarModelDto;
import su.vistar.monitoring.dto.PageDto;
import su.vistar.monitoring.entities.CarModel;
import su.vistar.monitoring.service.CarModelService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/carModel")
public class CarModelController {
    private final CarModelService service;

    @GetMapping("/{id}")
    public ResponseEntity<CarModelDto> getCarModelById(@PathVariable Long id) {
        return new ResponseEntity<>(new CarModelDto(service.getCarModelById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CarModelDto>> getCarModels(){
        return new ResponseEntity<>(service.getCarModels()
                .stream()
                .map(CarModelDto::new)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/size={pageSize}/page={pageNum}")
    public ResponseEntity<Page<CarModelDto>> getAllCarModelsPageable(@PathVariable int pageSize, @PathVariable int pageNum) {
        Page<CarModelDto> page = new PageImpl<>(service.getCarModelsPageable(new PageDto(pageSize, pageNum))
                .stream()
                .map(CarModelDto::new)
                .collect(Collectors.toList()));
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping("/engine={engineId}/fuel={fuelTypeId}")
    public ResponseEntity<CarModelDto> addCarModel(@RequestBody CarModel carModel,
                                                   @PathVariable Long engineId,
                                                   @PathVariable Long fuelTypeId){
        return new ResponseEntity<>(new CarModelDto(
                service.addCarModel(new CarModelDto(carModel), engineId, fuelTypeId)),
                HttpStatus.CREATED);
    }

    @PutMapping("/engine={engineId}/fuel={fuelTypeId}")
    public ResponseEntity<CarModelDto> updateCarModel(@RequestBody CarModel carModel,
                                                      @PathVariable Long engineId,
                                                      @PathVariable Long fuelTypeId) {
        return new ResponseEntity<>(new CarModelDto(
                service.updateCarModel(new CarModelDto(carModel), engineId, fuelTypeId)),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }
}
