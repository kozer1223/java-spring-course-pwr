package com.example.service.impl;

import com.example.service.WeatherService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Kacper on 2017-03-14.
 */
@Service("ugly")
public class UglyWeatherServiceImpl implements WeatherService {

    @Override
    public String getWeather() {
        return "It's rainy";
    }

}
