package com.example.impl;

import com.example.model.ExchangeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
