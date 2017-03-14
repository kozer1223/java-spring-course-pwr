package com.example.service.impl;

import com.example.service.WeatherService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Kacper on 2017-03-14.
 */
@Profile("cuteProfile")
@Service("cute")
public class CuteWeatherServiceImpl implements WeatherService {

    @Override
    public String getWeather() {
        return "It's wonderful";
    }

}
