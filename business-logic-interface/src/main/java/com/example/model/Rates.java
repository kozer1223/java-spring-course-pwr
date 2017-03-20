package com.example.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({

})
public class Rates {

    @JsonIgnore
    private Map<String, BigDecimal> currencyRates = new HashMap<String, BigDecimal>();

    @JsonAnyGetter
    public Map<String, BigDecimal> getCurrencyRates() {
        return this.currencyRates;
    }

    @JsonAnySetter
    public void setCurrencyRates(String name, BigDecimal value) {
        this.currencyRates.put(name, value);
    }

}
