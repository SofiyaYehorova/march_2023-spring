package com.sofiya.march_2023spring.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.sofiya.march_2023spring.dto.CarDto;
import com.sofiya.march_2023spring.models.Car;
import com.sofiya.march_2023spring.services.CarService;
import com.sofiya.march_2023spring.view.View;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cars")
public class CarController {

    private final CarService carService;

    @JsonView(View.Level3.class)
    @GetMapping
    public ResponseEntity<List<CarDto>> getAll() {
        return ResponseEntity.ok(this.carService.getAll());
    }

    @PostMapping
    public ResponseEntity<CarDto> create(@RequestBody @Valid CarDto carDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.carService.create(carDto));
    }

    @JsonView(View.Level1.class)
    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getById(@PathVariable int id) {
        return ResponseEntity.of(this.carService.getById(id));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Car> updateCar(@PathVariable int id, @RequestBody Car car) {
//        car.setId(id);
//        Car updatedCar = carService.updateCar(car);
//        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
//    }
//
//    @PatchMapping("/{id}")
//    public Car patchCarFields(@PathVariable int id, @RequestBody Map<String, Object> fields){
//        return carService.patchCarByFields(id, fields);
//    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        this.carService.deleteById(id);
    }

    @JsonView(View.Level2.class)
    @GetMapping("/power/{value}")
    public ResponseEntity<List<CarDto>> getAllByPower(@PathVariable int value) {
        return ResponseEntity.ok(this.carService.getByPower(value));
    }

    @JsonView(View.Level2.class)
    @GetMapping("/producer/{value}")
    public ResponseEntity<List<CarDto>> getByProducer(@PathVariable String value) {
        return ResponseEntity.ok(this.carService.getByProducer(value));
    }
}
