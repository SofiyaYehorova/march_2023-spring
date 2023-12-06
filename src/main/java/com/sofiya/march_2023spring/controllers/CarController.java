package com.sofiya.march_2023spring.controllers;

import com.sofiya.march_2023spring.models.Car;
import com.sofiya.march_2023spring.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cars")
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        return ResponseEntity.ok(this.carService.getAll());
    }

    @PostMapping
    public ResponseEntity<Car> create(@RequestBody Car car) {
        return ResponseEntity.ok(this.carService.create(car));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable int id) {
        return ResponseEntity.of(this.carService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable int id, @RequestBody Car car) {
        car.setId(id);
        Car updatedCar = carService.updateCar(car);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public Car patchCarFields(@PathVariable int id, @RequestBody Map<String, Object> fields){
        return carService.patchCarByFields(id, fields);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        this.carService.deleteById(id);
    }

    @GetMapping("/power/{value}")
    public ResponseEntity<List<Car>> getAllByPower(@PathVariable int value) {
        return ResponseEntity.ok(this.carService.getByPower(value));
    }

    @GetMapping("/producer/{value}")
    public ResponseEntity<List<Car>> getByProducer(@PathVariable String value) {
        return ResponseEntity.ok(this.carService.getByProducer(value));
    }
}
