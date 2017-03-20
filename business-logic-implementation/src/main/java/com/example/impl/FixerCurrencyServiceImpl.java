package com.example.impl;

import com.example.model.ExchangeModel;
import com.example.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;

/**
 * Created by Kacper on 2017-03-20.
 */
@Service
public class FixerCurrencyServiceImpl implements CurrencyService {

    private final String BASE_URL = "http://api.fixer.io/";
    private final String LATEST_REQUEST = "latest";
    private final String SOURCE_REQUEST = "base=";
    private final String TARGET_REQUEST = "symbols=";
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    RestTemplate rest;

    private ExchangeModel fullExchangeRates(Currency source, Collection<Currency> target, Date date){
        if (source == null){
            throw new IllegalArgumentException("Source currency cannot be null");
        }
        StringBuilder url = new StringBuilder();
        url.append(BASE_URL);
        if (date == null){
            url.append(LATEST_REQUEST);
        } else {
            url.append(dateFormat.format(date));
        }
        url.append("?");
        url.append(SOURCE_REQUEST);
        url.append(source.getCurrencyCode());
        if(target != null && !target.isEmpty()){
            StringBuilder targetUrl = new StringBuilder();
            targetUrl.append(TARGET_REQUEST);
            target.forEach(x -> targetUrl.append(x.getCurrencyCode() +","));
            targetUrl.deleteCharAt(targetUrl.length() - 1); // remove last ','
            url.append("&").append(targetUrl);
        }

        ExchangeModel response = rest.getForObject(url.toString(), ExchangeModel.class);
        return response;
    }

    @Override
    public ExchangeModel getExchangeRates(Currency source) {
        return fullExchangeRates(source, null, null);
    }

    @Override
    public ExchangeModel getExchangeRates(Currency source, Currency target) {
        return fullExchangeRates(source, Collections.singleton(target), null);
    }

    @Override
    public ExchangeModel getExchangeRates(Currency source, Currency target, Date date) {
        return fullExchangeRates(source, Collections.singleton(target), date);
    }

    @Override
    public ExchangeModel getExchangeRates(Currency source, Collection<Currency> targets) {
        return fullExchangeRates(source, targets, null);
    }

    @Override
    public ExchangeModel getExchangeRates(Currency source, Collection<Currency> targets, Date date) {
        return fullExchangeRates(source, targets, date);
    }
}
