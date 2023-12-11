package com.sofiya.march_2023spring.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.sofiya.march_2023spring.dto.CarDto;
import com.sofiya.march_2023spring.models.Car;
import com.sofiya.march_2023spring.services.CarService;
import com.sofiya.march_2023spring.view.View;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
//    public ResponseEntity<CarDto> update(@PathVariable int id, @RequestBody @Valid CarDto carDto) {
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.carService.update(id,carDto));
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<CarDto> updateCar(@PathVariable int id, @RequestBody CarDto carDto) {
//        carDto.setId(id);
//        CarDto updatedCar = carService.updateCar(carDto);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedCar);
//    }


//    @ResponseStatus(HttpStatus.OK)
//    public void updatePost(@PathVariable int id, @RequestBody CarDto carDto) {
//        if(!Objects.equals(id, carDto.getId())){
//            throw new IllegalArgumentException("IDs don't match");
//        }
//        Post post = convertToEntity(postDto);
//        postService.updatePost(post);
//    }
//    private CarDto convertToDto(Car car) {
////        PostDto postDto = modelMapper.map(post, PostDto.class);
////        modelMapper.map
//        postDto.setSubmissionDate(post.getSubmissionDate(),
//                userService.getCurrentUser().getPreference().getTimezone());
//        return postDto;
//    }
//}



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

    @SneakyThrows
    @JsonView(View.Level1.class)
    @PostMapping("/{id}/photo")
    public ResponseEntity<CarDto> addPhotoToCarById(@PathVariable int id, MultipartFile photo){
        return ResponseEntity.ok(this.carService.addPhotoByCarId(id, photo));
    }
}
