package su.vistar.monitoring.controller;

import su.vistar.monitoring.entities.FuelType;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import su.vistar.monitoring.service.FuelTypeService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/fuelType")
public class FuelTypeController {
    FuelTypeService fuelTypeService;

    @GetMapping("/{id}")
    public ResponseEntity<FuelType> getFuelTypeById(@PathVariable Long id) {
        return new ResponseEntity<>(fuelTypeService.getFuelTypeById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FuelType>> getFuelTypes(){
        return new ResponseEntity<>(fuelTypeService.getFuelTypes(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FuelType> addFuelType(@RequestBody FuelType fuelType) {
        return new ResponseEntity<>(fuelTypeService.addFuelType(fuelType), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuelType> updateFuelType(@PathVariable Long id, @RequestBody FuelType fuelType) {
        return new ResponseEntity<>(fuelTypeService.updateFuelType(id, fuelType),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        fuelTypeService.deleteById(id);
    }
}
