package com.sofiya.march_2023spring.controllers;

import com.sofiya.march_2023spring.models.Car;
import com.sofiya.march_2023spring.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cars")
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll(){
        return ResponseEntity.ok(this.carService.getAll());
    }
    @PostMapping
    public ResponseEntity<Car> create(@RequestBody Car car){
        return ResponseEntity.ok(this.carService.create(car));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable int id){
        return ResponseEntity.of(this.carService.getById(id));
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        this.carService.deleteById(id);
    }
    @GetMapping("/power/{value}")
    public ResponseEntity <List<Car>> getAllByPower(@PathVariable int value){
        return ResponseEntity.ok(this.carService.getByPower(value));
    }
    @GetMapping("/producer/{value}")
    public ResponseEntity<List<Car>> getByProducer(@PathVariable String value){
        return ResponseEntity.ok(this.carService.getByProducer(value));
    }
}
