package com.example.controller;

import com.example.model.CurrencyParams;
import com.example.model.ExchangeModel;
import com.example.model.WeatherParams;
import com.example.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

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

}
