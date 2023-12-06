package com.sofiya.march_2023spring.services;

import com.sofiya.march_2023spring.models.Car;
import com.sofiya.march_2023spring.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
//    CarDto completeTodo(int id);

    public List<Car> getAll() {
        return this.carRepository.findAll();
    }

    public Optional<Car> getById(int id) {
        return this.carRepository.findById(id);
    }

    public Car create(Car car) {
        return this.carRepository.save(car);
    }

    public void deleteById(int id) {
        this.carRepository.deleteById(id);
    }

    public Car updateCar(Car car) {
        Car foundCar = carRepository.findById(car.getId()).get();
        foundCar.setModel(car.getModel());
        foundCar.setPower(car.getPower());
        foundCar.setProducer(car.getProducer());
        return carRepository.save(foundCar);
    }

    public Car patchCarByFields(int id, Map<String, Object> fields) {
        Optional<Car> existingCar = carRepository.findById(id);
        if (existingCar.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Car.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingCar.get(), value);
            });
            return carRepository.save(existingCar.get());
        }
        return null;
    }

    public List<Car> getByPower(int power) {
        return this.carRepository.findAllByPower(power);
    }

    public List<Car> getByProducer(String producer) {
        return this.carRepository.findAllByProducer(producer);
    }


}
