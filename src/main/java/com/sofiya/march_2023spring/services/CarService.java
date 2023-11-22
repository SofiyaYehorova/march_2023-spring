package com.sofiya.march_2023spring.services;

import com.sofiya.march_2023spring.models.Car;
import com.sofiya.march_2023spring.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<Car> getAll(){
        return this.carRepository.findAll();
    }
    public Optional<Car> getById(int id){
        return this.carRepository.findById(id);
    }
    public Car create (Car car){
        return this.carRepository.save(car);
    }
    public void deleteById(int id){
        this.carRepository.deleteById(id);
    }
    public List<Car> getByPower(int power){
        return this.carRepository.findAllByPower(power);
    }
    public List<Car> getByProducer(String producer){
        return this.carRepository.findAllByProducer(producer);
    }
}
