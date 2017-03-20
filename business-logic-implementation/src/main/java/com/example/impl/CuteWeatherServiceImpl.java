package com.example.impl;

import com.example.service.WeatherService;
import org.springframework.stereotype.Service;

/**
 * Created by Kacper on 2017-03-14.
 */
@Service("cute")
public class CuteWeatherServiceImpl implements WeatherService {

    @Override
    public String getWeather() {
        return "It's wonderful";
    }

}
