package com.example.controller;

import com.example.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * Created by Kacper on 2017-03-14.
 */
@RestController
@RequestMapping("/api")
public class WeatherController {

/*    @Qualifier("cute")
    @Autowired //Injected, Named, ...
    private WeatherService cuteWeatherService;

    @Qualifier("ugly")
    @Autowired //Injected, Named, ...
    private WeatherService uglyWeatherService;

    @Autowired
    private Collection<WeatherService> weatherServices;*/

    @Autowired
    private WeatherService weatherService;

    @RequestMapping("/weather")
    public String getWeather() {
        /*String weather = "";
        for (WeatherService service : weatherServices){
            weather += service.getWeather() + " ";
        }
        return weather;*/
        return weatherService.getWeather();
    }

/*    @RequestMapping("/weather/cute")
    public String getCuteWeather() {
        return cuteWeatherService.getWeather();
    }

    @RequestMapping("/weather/ugly")
    public String getUglyWeather() {
        return uglyWeatherService.getWeather();
    }*/

}
