package com.example.controller;

import com.example.model.CurrencyParams;
import com.example.model.ExchangeModel;
import com.example.model.WeatherParams;
import com.example.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Kacper on 2017-03-20.
 */
@RestController
@RequestMapping("/mainapi")
public class MainCurrencyController {

    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    CurrencyService currencyService;

    @RequestMapping("/currency")
    ResponseEntity<ExchangeModel> getExchange(@RequestParam(value = "from", required = true) String from,
                                              @RequestParam(value = "to", required = false) String to,
                                              @RequestParam(value = "date", required = false) String date){
        try{
            Currency sourceCurrency = Currency.getInstance(from);
            List<Currency> targetCurrencies = new ArrayList<>();
            if (to != null){
                String[] tokens = to.split(",");
                for (String token : tokens){
                    targetCurrencies.add(Currency.getInstance(token));
                }
            }
            Date dateDto = null;
            if (date != null){
                dateDto = dateFormat.parse(date);
            }
            return new ResponseEntity<ExchangeModel>(currencyService.getExchangeRates(sourceCurrency, targetCurrencies, dateDto), HttpStatus.OK);
        } catch (IllegalArgumentException | ParseException e){
            return new ResponseEntity<ExchangeModel>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/currency", method = RequestMethod.POST)
    ResponseEntity<ExchangeModel> getExchange(@RequestBody CurrencyParams params){
        return getExchange(params.getFrom(), params.getTo(), params.getDate());
    }

    @RequestMapping("/exchange")
    ResponseEntity<BigDecimal> getExchangeValue(@RequestParam("value") BigDecimal value,
                                                @RequestParam("from") String from,
                                                @RequestParam(value = "to", required = false) String to,
                                                @RequestParam(value = "date", required = false) String date){
        try{
            Currency sourceCurrency = Currency.getInstance(from);
            Currency targetCurrency = Currency.getInstance(to);
            Date dateDto = null;
            if (date != null){
                dateDto = dateFormat.parse(date);
            }
            ExchangeModel model = currencyService.getExchangeRates(sourceCurrency, targetCurrency, dateDto);
            Map<String, BigDecimal> rates = model.getRates().getCurrencyRates();
            if (!rates.keySet().isEmpty()){
                BigDecimal exchangedValue = value.multiply(rates.get(targetCurrency.getCurrencyCode()));
                return new ResponseEntity<BigDecimal>(exchangedValue, HttpStatus.OK);
            } else {
                return new ResponseEntity<BigDecimal>(HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException | ParseException e){
            return new ResponseEntity<BigDecimal>(HttpStatus.BAD_REQUEST);
        }
    }

}
