package com.example.impl;

import com.example.entity.CurrencyData;
import com.example.entity.CurrencyValue;
import com.example.model.ExchangeModel;
import com.example.model.Rates;
import com.example.repository.CurrencyDataRepository;
import com.example.repository.CurrencyValueRepository;
import matchers.CurrencyMatcher;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Kacper on 2017-04-03.
 */
@RunWith(MockitoJUnitRunner.class)
public class FixerCurrencyServiceImplTest {

    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @InjectMocks
    FixerCurrencyServiceImpl fixerCurrencyService = new FixerCurrencyServiceImpl();

    @Mock
    RestTemplate rest;

    @Mock
    private CurrencyDataRepository currencyDataRepository;

    @Mock
    private CurrencyValueRepository currencyValueRepository;

    @Before
    public void setup(){
        ExchangeModel response = new ExchangeModel();
        response.setBase("EUR");
        response.setDate(dateFormat.format(new Date()));
        Rates rates = new Rates();
        rates.setCurrencyRates("PLN", BigDecimal.valueOf(1.2));
        response.setRates(rates);
        Mockito.when(rest.getForObject(Mockito.anyString(), Mockito.eq(ExchangeModel.class))).thenReturn(response);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullCurrencyExchangeRatesThrowsException(){
        fixerCurrencyService.getExchangeRates(null);
    }

    @Test
    public void testGetExchangeRatesNormalCurrenciesReturnsNotNull(){
        Assert.assertNotNull(fixerCurrencyService.getExchangeRates(Currency.getInstance("EUR"), Currency.getInstance("PLN")));
    }

    @Test
    public void testGetExchangeRatesNormalCurrenciesWithDateReturnsNotNull(){
        Assert.assertNotNull(fixerCurrencyService.getExchangeRates(Currency.getInstance("EUR"), Currency.getInstance("PLN"), new Date()));
    }

    @Test
    public void testPairsContainsPLN(){
        List<CurrencyValue> data = new ArrayList<CurrencyValue>();
        CurrencyData plnCurrency = new CurrencyData(0L, "PLN", "Polish Zloty");
        CurrencyData eurCurrency = new CurrencyData(1L, "EUR", "Euro");
        CurrencyData usdCurrency = new CurrencyData(2L, "USD", "US Dollar");
        CurrencyData gbpCurrency = new CurrencyData(3L, "GBP", "Great Britain Pound");
        data.add(new CurrencyValue(0L, plnCurrency, eurCurrency, BigDecimal.ONE, new java.sql.Date(new Date().getTime())));
        data.add(new CurrencyValue(1L, plnCurrency, gbpCurrency, BigDecimal.ONE, new java.sql.Date(new Date().getTime())));
        data.add(new CurrencyValue(2L, usdCurrency, plnCurrency, BigDecimal.ONE, new java.sql.Date(new Date().getTime())));
        data.add(new CurrencyValue(3L, gbpCurrency, usdCurrency, BigDecimal.ONE, new java.sql.Date(new Date().getTime())));
        data.add(new CurrencyValue(4L, eurCurrency, usdCurrency, BigDecimal.ONE, new java.sql.Date(new Date().getTime())));
        Mockito.when(currencyValueRepository.findAll()).thenReturn(data);
        Assert.assertThat(fixerCurrencyService.getAllExchangePairs(), CoreMatchers.hasItem(CurrencyMatcher.exchangeFrom(plnCurrency)));
    }
}
