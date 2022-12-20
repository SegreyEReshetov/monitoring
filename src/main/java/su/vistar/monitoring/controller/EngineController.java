package su.vistar.monitoring.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import su.vistar.monitoring.dto.EngineDto;
import su.vistar.monitoring.dto.PageDto;
import su.vistar.monitoring.service.EngineService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/engine")
public class EngineController {
    private final EngineService service;

    @GetMapping("/{id}")
    public ResponseEntity<EngineDto> getEngineById(@PathVariable Long id) {
        return new ResponseEntity<>(new EngineDto(service.getEngineById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EngineDto>> getEngines(){
        return new ResponseEntity<>(service.getEngines()
                .stream()
                .map(EngineDto::new)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/size={pageSize}/page={pageNum}")
    public ResponseEntity<Page<EngineDto>> getAllEnginesPageable(@PathVariable int pageSize, @PathVariable int pageNum) {
        Page<EngineDto> page = new PageImpl<>(service.getEnginesPageable(new PageDto(pageSize, pageNum))
                .stream()
                .map(EngineDto::new)
                .collect(Collectors.toList()));
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EngineDto> addEngine(@RequestBody EngineDto engineDto){
        return new ResponseEntity<>(new EngineDto(service.addEngine(engineDto)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EngineDto> updateEngine(@RequestBody EngineDto engineDto){
        return new ResponseEntity<>(new EngineDto(service.updateEngine(engineDto)),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }
}
