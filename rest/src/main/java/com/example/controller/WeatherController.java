package com.example.controller;

import com.example.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kacper on 2017-03-14.
 */
@RestController
@RequestMapping("/api")
public class WeatherController {

    @Autowired
    private Collection<WeatherService> weatherServices;

    @Autowired
    @Qualifier("otherWeatherType")
    private String myBean;

    @RequestMapping("/allWeathers")
    public List<String> allWeathers() {
        List<String> result = new ArrayList<String>();
        weatherServices.forEach(x -> result.add(x.getWeather()));
        result.add(myBean);
        return result;
    }

    @RequestMapping("/weather")
    public String getWeather() {
        String weather = "";
        for (WeatherService service : weatherServices){
            weather += service.getWeather();
        }
        return weather;
    }

}
