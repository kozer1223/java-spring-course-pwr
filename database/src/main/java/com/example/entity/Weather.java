package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by Kacper on 2017-03-21.
 */

@Data
@Entity
@Table(name="weather")
public class Weather {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String city;

    @Column
    private Double temp;

    @Column
    private Double pressure;

    @Column
    private Time time;

}
