package com.example.service;

import com.example.util.ExchangeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kacper on 2017-03-16.
 */
@Service
public class OpenWeatherClient {

    String url = "http://api.fixer.io/latest?symbols=USD,GBP&base=PLN";

    @Autowired
    RestTemplate rest;

    public ExchangeModel getExchange() {
        ExchangeModel response = rest.getForObject(url, ExchangeModel.class);
        return response;
    }
}
