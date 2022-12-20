package su.vistar.monitoring.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import su.vistar.monitoring.dto.CarDto;
import su.vistar.monitoring.dto.PageDto;
import su.vistar.monitoring.entities.Car;
import su.vistar.monitoring.service.CarService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/car")
public class CarController {
    private final CarService service;

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        return new ResponseEntity<>(new CarDto(service.getCarById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars(){
        return new ResponseEntity<>(service.getCars(), HttpStatus.OK);
    }

    @GetMapping("/size={pageSize}/page={pageNum}")
    public ResponseEntity<Page<CarDto>> getAllCarsPageable(@PathVariable int pageSize, @PathVariable int pageNum) {
        Page<CarDto> page = new PageImpl<>(service.getCarsPageable(new PageDto(pageSize, pageNum))
                .stream()
                .map(CarDto::new)
                .collect(Collectors.toList()));
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto carDto){
        return new ResponseEntity<>(new CarDto(service.addCar(carDto)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto){
        return new ResponseEntity<>(new CarDto(service.updateCar(carDto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }
}
