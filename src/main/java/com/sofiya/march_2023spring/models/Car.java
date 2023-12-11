package com.sofiya.march_2023spring.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="cars")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    private int power;
    private String producer;
    private String photo;
}
