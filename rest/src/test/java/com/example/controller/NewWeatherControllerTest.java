package com.example.controller;

import com.example.impl.OpenWeatherClient;
import com.example.model.ExchangeModel;
import com.example.service.OpenWeatherMapClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

/**
 * Created by Kacper on 2017-03-28.
 */

public class NewWeatherControllerTest {

    @Mock(answer = Answers.RETURNS_MOCKS)
    private OpenWeatherClient client;

    @Mock
    private OpenWeatherMapClient weatherClient;

    @InjectMocks
    NewWeatherController sut;

    @Before
    public void setUp(){

    }

    @Test
    public void shouldReturnWeather(){
        ExchangeModel model = sut.getExchange();
        Assert.assertNotNull(model);
    }

}
