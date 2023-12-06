package com.sofiya.march_2023spring.mapper;

import com.sofiya.march_2023spring.dto.CarDto;
import com.sofiya.march_2023spring.models.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public CarDto toDto(Car car){
        return CarDto.builder()
                .id(car.getId())
                .model(car.getModel())
                .power(car.getPower())
                .producer(car.getProducer())
                .build();
    }
    public Car toEntity(CarDto carDto){
        Car car = new Car();
        car.setModel(carDto.getModel());
        car.setPower(carDto.getPower());
        car.setProducer(carDto.getProducer());
        return car;
    }
}
