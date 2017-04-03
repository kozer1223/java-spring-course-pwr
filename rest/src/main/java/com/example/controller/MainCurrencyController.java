package com.example.controller;

import com.example.entity.CurrencyData;
import com.example.entity.CurrencyValue;
import com.example.model.CurrencyParams;
import com.example.model.CurrencyStatParams;
import com.example.model.ExchangeModel;
import com.example.model.WeatherParams;
import com.example.repository.CurrencyDataRepository;
import com.example.repository.CurrencyValueRepository;
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

    @Autowired
    private CurrencyDataRepository currencyDataRepository;

    @Autowired
    private CurrencyValueRepository currencyValueRepository;

    @RequestMapping("/currency")
    ResponseEntity<ExchangeModel> getExchange(@RequestParam(value = "from", required = true) String from,
                                              @RequestParam(value = "to", required = false) String to,
                                              @RequestParam(value = "date", required = false) String date) {
        try {
            Currency sourceCurrency = Currency.getInstance(from);
            List<Currency> targetCurrencies = new ArrayList<>();
            if (to != null) {
                String[] tokens = to.split(",");
                for (String token : tokens) {
                    targetCurrencies.add(Currency.getInstance(token));
                }
            }
            Date dateDto = null;
            if (date != null) {
                dateDto = dateFormat.parse(date);
            }
            return new ResponseEntity<ExchangeModel>(currencyService.getExchangeRates(sourceCurrency, targetCurrencies, dateDto), HttpStatus.OK);
        } catch (IllegalArgumentException | ParseException e) {
            return new ResponseEntity<ExchangeModel>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/currency", method = RequestMethod.POST)
    ResponseEntity<ExchangeModel> getExchange(@RequestBody CurrencyParams params) {
        return getExchange(params.getFrom(), params.getTo(), params.getDate());
    }

    @RequestMapping("/exchange")
    ResponseEntity<BigDecimal> getExchangeValue(@RequestParam("value") BigDecimal value,
                                                @RequestParam("from") String from,
                                                @RequestParam(value = "to", required = false) String to,
                                                @RequestParam(value = "date", required = false) String date) {
        try {
            Currency sourceCurrency = Currency.getInstance(from);
            Currency targetCurrency = Currency.getInstance(to);
            Date dateDto = null;
            if (date != null) {
                dateDto = dateFormat.parse(date);
            }
            ExchangeModel model = currencyService.getExchangeRates(sourceCurrency, targetCurrency, dateDto);
            Map<String, BigDecimal> rates = model.getRates().getCurrencyRates();
            if (!rates.keySet().isEmpty()) {
                BigDecimal exchangedValue = value.multiply(rates.get(targetCurrency.getCurrencyCode()));
                return new ResponseEntity<BigDecimal>(exchangedValue, HttpStatus.OK);
            } else {
                return new ResponseEntity<BigDecimal>(HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException | ParseException e) {
            return new ResponseEntity<BigDecimal>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getLastCurrency", method = RequestMethod.GET)
    CurrencyData getLastCurrency(){
        List<CurrencyData> currencies = currencyDataRepository.findAll();
        return currencies.get(currencies.size() - 1);
    }

    @RequestMapping(value = "/getLastCurrencyValue", method = RequestMethod.GET)
    CurrencyValue getLastCurrencyValue(){
        List<CurrencyValue> currencies = currencyValueRepository.findAll();
        return currencies.get(currencies.size() - 1);
    }

    @RequestMapping(value = "/getCurrency/{id}", method = RequestMethod.GET)
    CurrencyData getCurrency(@PathVariable Long id){
        return currencyDataRepository.findOne(id);
    }

    @RequestMapping(value = "/getCurrencyValue/{id}", method = RequestMethod.GET)
    CurrencyValue getCurrencyValue(@PathVariable Long id){
        return currencyValueRepository.findOne(id);
    }

    @RequestMapping(value="/putCurrency", method = RequestMethod.PUT)
    Long putCurrency(@RequestBody CurrencyData currencyData){
        currencyDataRepository.save(currencyData);
        return currencyDataRepository.count();
    }

    @RequestMapping(value="/putCurrencyValue", method = RequestMethod.PUT)
    Long putCurrencyValue(@RequestBody CurrencyValue currencyValue){
        currencyValueRepository.save(currencyValue);
        return currencyValueRepository.count();
    }

    @RequestMapping(value = "/deleteCurrency/{id}", method = RequestMethod.DELETE)
    ResponseEntity<String> deleteCurrency(@PathVariable Long id){
        currencyDataRepository.delete(id);
        return new ResponseEntity<String>("DELETED", HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteCurrencyValue/{id}", method = RequestMethod.DELETE)
    ResponseEntity<String> deleteCurrencyValue(@PathVariable Long id){
        currencyValueRepository.delete(id);
        return new ResponseEntity<String>("DELETED", HttpStatus.OK);
    }

    @RequestMapping(value="/getCurrencyStats", method = RequestMethod.POST)
    String getCurrencyStatistics(@RequestBody CurrencyStatParams currencyStatParams){
        List<CurrencyValue> currencyValues = currencyValueRepository.findByBaseCurrencyCurrencyCodeAndDateBetween(
                currencyStatParams.getCurrency(),currencyStatParams.getStartDate(), currencyStatParams.getEndDate());
        List<BigDecimal> values = new ArrayList<>();
        for (CurrencyValue currencyValue : currencyValues){
            values.add(currencyValue.getValue());
        }
        BigDecimal min = Collections.min(values);
        BigDecimal max = Collections.max(values);
        BigDecimal avg = BigDecimal.ZERO;
        for (BigDecimal value : values){
            avg = avg.add(value);
        }
        return currencyValues.toString();
        //avg = avg.divide(BigDecimal.valueOf(values.size()));
        //return "MIN: " + min.toString() + " MAX: " + max.toString() + " AVG: " + avg.toString();
    }

}
