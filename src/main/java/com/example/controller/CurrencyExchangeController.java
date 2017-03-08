package com.example.controller;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;

/**
 * Created by Kacper on 2017-03-07.
 */

@RestController
public class CurrencyExchangeController {

    @RequestMapping("/multiply/{number}")
    public Long multiplyByTwo(@PathVariable Long number){
        return number * 2;
    }

    @RequestMapping("/currency/{value}")
    public String addCurrencySignature(@PathVariable Long value,
                                       @RequestParam("currency") String currency){
        try{
            Currency currencyObject = Currency.getInstance(currency); // bad variable name???
            return value + " " + currency;
        } catch (IllegalArgumentException e){
            throw new RuntimeException("Something went wrong.");
        }
    }

    @RequestMapping("/currency/{value}/{multiplier}")
    public String calculateCurrency(@PathVariable Long value,
                                    @PathVariable Long multiplier,
                                    @RequestParam("from") String from,
                                    @RequestParam("to") String to){
        try{
            Currency currencyFrom = Currency.getInstance(from);
            Currency currencyTo = Currency.getInstance(to);
            return value + " " + from + " = " + value * multiplier + " " + to;
        } catch (IllegalArgumentException e){
            throw new RuntimeException("Something went wrong.");
        }
    }

}
