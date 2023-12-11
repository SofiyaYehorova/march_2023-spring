package com.sofiya.march_2023spring.services;

import com.sofiya.march_2023spring.dto.CarDto;
import com.sofiya.march_2023spring.mapper.CarMapper;
import com.sofiya.march_2023spring.models.Car;
import com.sofiya.march_2023spring.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final MailService mailService;

    public List<CarDto> getAll() {
        return this.carRepository.findAll().stream().map(carMapper::toDto).toList();
    }

    public Optional<CarDto> getById(int id) {
        return this.carRepository.findById(id).stream().map(carMapper::toDto).findFirst();
    }

    public CarDto create(CarDto carDto) {
        CarDto createdCar = carMapper.toDto(this.carRepository.save(carMapper.toEntity(carDto)));
        this.mailService.notifyCreatedCar(createdCar);
        return createdCar;
    }

    public void deleteById(int id) {
        this.carRepository.deleteById(id);
        this.mailService.notifyDeletedCar(id);
    }

//    public CarDto update(int id, CarDto carDto) {
//        Car carById = this.carRepository.findById(id);
//        return carMapper.toDto(carById);
//    }
//        return carMapper.toDto(this.carRepository.findById(id)
//                //                .save(carMapper.toEntity(carDto)));
//    }
    //    public Car updateCar(Car car) {
//        Car foundCar = carRepository.findById(car.getId()).get();
//        foundCar.setModel(car.getModel());
//        foundCar.setPower(car.getPower());
//        foundCar.setProducer(car.getProducer());
//        return carRepository.save(foundCar);
//    }
//    public void updateCar(CarDto carDto) {
//        Car foundCar = carRepository.findById(carDto.getId()).get();
//        foundCar.setModel(carDto.getModel());
//        foundCar.setPower(carDto.getPower());
//        foundCar.setProducer(carDto.getProducer());
////        return carRepository.save(foundCar);
//        return carMapper.toDto(this.carRepository.save(carMapper.toEntity(foundCar)));
//    }
//    @PutMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void updatePost(@PathVariable("id") Long id, @RequestBody PostDto postDto) {
//        if(!Objects.equals(id, postDto.getId())){
//            throw new IllegalArgumentException("IDs don't match");
//        }
//        Post post = convertToEntity(postDto);
//        postService.updatePost(post);
//    }
//}

    //    @PutMapping("/{id}")
//    public ResponseEntity<CarDto> updatePost(@PathVariable int id, @RequestBody CarDto carDto) {
//
//        // convert DTO to Entity
////        Post postRequest = modelMapper.map(postDto, Post.class);
//
//        Car carMapperEntity = carMapper.toEntity(carDto, Car.class);
//
////        Post post = postService.updatePost(id, postRequest);
//        Car car = updateCar(id, carMapperEntity);
//        // entity to DTO
////        PostDto postResponse = modelMapper.map(post, PostDto.class);
//        CarDto carMapperDto = carMapper.toDto(car, CarDto.class);
//
////        return ResponseEntity.ok().body(postResponse);
//        return ResponseEntity.ok().body(carMapperDto);
//    }

//    public Car patchCarByFields(int id, Map<String, Object> fields) {
//        Optional<Car> existingCar = carRepository.findById(id);
//        if (existingCar.isPresent()) {
//            fields.forEach((key, value) -> {
//                Field field = ReflectionUtils.findField(Car.class, key);
//                field.setAccessible(true);
//                ReflectionUtils.setField(field, existingCar.get(), value);
//            });
//            return carRepository.save(existingCar.get());
//        }
//        return null;
//    }

    public List<CarDto> getByPower(int power) {

        return this.carRepository.findAllByPower(power).stream().map(carMapper::toDto).toList();
    }

    public List<CarDto> getByProducer(String producer) {
        return this.carRepository.findAllByProducer(producer).stream().map(carMapper::toDto).toList();
    }

    public CarDto addPhotoByCarId(int id, MultipartFile file) throws IOException {
        Car car = this.carRepository.findById(id).orElseThrow();
        String originalFilename = file.getOriginalFilename();
        String path = System.getProperty("user.home") + File.separator + "cars" + File.separator + originalFilename;
        file.transferTo(new File(path));
        car.setPhoto(originalFilename);
        Car savedCar = this.carRepository.save(car);
        return this.carMapper.toDto(savedCar);
    }


}
