package com.example.service.impl;

import com.example.service.WeatherService;
import com.example.service.impl.conditions.LinuxCondition;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Created by Kacper on 2017-03-14.
 */
@Conditional(value = LinuxCondition.class)
@Service("cute")
public class CuteWeatherServiceImpl implements WeatherService {

    @Override
    public String getWeather() {
        return "It's wonderful";
    }

}
