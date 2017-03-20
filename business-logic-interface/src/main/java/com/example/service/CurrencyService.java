package com.example.service;

import com.example.model.ExchangeModel;

import java.util.Collection;
import java.util.Currency;
import java.util.Date;

/**
 * Created by Kacper on 2017-03-20.
 */
public interface CurrencyService {

    ExchangeModel getExchangeRates(Currency source);
    ExchangeModel getExchangeRates(Currency source, Currency target);
    ExchangeModel getExchangeRates(Currency source, Currency target, Date date);
    ExchangeModel getExchangeRates(Currency source, Collection<Currency> targets);
    ExchangeModel getExchangeRates(Currency source, Collection<Currency> targets, Date date);

}
