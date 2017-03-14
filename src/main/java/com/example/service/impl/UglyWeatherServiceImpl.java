package com.example.service.impl;

import com.example.service.WeatherService;
import com.example.service.impl.conditions.WindowsCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

/**
 * Created by Kacper on 2017-03-14.
 */
@Conditional(value = WindowsCondition.class)
@Service("ugly")
public class UglyWeatherServiceImpl implements WeatherService {

    @Override
    public String getWeather() {
        return "It's rainy";
    }

}
