package com.example.controller;

import com.example.model.WeatherParams;
import com.example.impl.OpenWeatherClient;
import com.example.model.ExchangeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Kacper on 2017-03-16.
 */

@RestController
@RequestMapping("/newapi")
public class NewWeatherController {

    @Autowired
    OpenWeatherClient client;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String sayHelo(@RequestParam(name="imie", required = false, defaultValue = "stranger") String name){
        return "Hello " + name;
    }

    @RequestMapping("/weather")
    WeatherParams getWeather(@Validated WeatherParams params){
        params.setCurrent(false);
        return params;
    }

    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    WeatherParams getWeatherPost(@RequestBody @Validated WeatherParams params){
        params.setCurrent(false);
        return params;
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST)
    ResponseEntity<WeatherParams> getWeatherStatus(@RequestBody @Validated WeatherParams params){
        params.setCurrent(false);
        return new ResponseEntity<WeatherParams>(params, HttpStatus.CREATED);
    }

    @RequestMapping("/currency")
    ExchangeModel getExchange(){
        return client.getExchange();
    }

}
