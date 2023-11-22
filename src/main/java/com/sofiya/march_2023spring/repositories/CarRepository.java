package com.sofiya.march_2023spring.repositories;

import com.sofiya.march_2023spring.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findAllByProducer(String producer);
    List<Car> findAllByPower(int power);
}
