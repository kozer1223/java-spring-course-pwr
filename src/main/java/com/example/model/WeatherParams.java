package com.example.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Size;

/**
 * Created by Kacper on 2017-03-16.
 */
@Data
public class WeatherParams {

    private String city;

    @Valid
    @Size(min = 2, max = 5)
    private String country;

    private Boolean current;

}
