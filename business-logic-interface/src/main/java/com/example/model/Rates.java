
package com.example.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "GBP",
    "USD"
})
public class Rates {

    @JsonProperty("GBP")
    private Double gBP;
    @JsonProperty("USD")
    private Double uSD;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("GBP")
    public Double getGBP() {
        return gBP;
    }

    @JsonProperty("GBP")
    public void setGBP(Double gBP) {
        this.gBP = gBP;
    }

    public Rates withGBP(Double gBP) {
        this.gBP = gBP;
        return this;
    }

    @JsonProperty("USD")
    public Double getUSD() {
        return uSD;
    }

    @JsonProperty("USD")
    public void setUSD(Double uSD) {
        this.uSD = uSD;
    }

    public Rates withUSD(Double uSD) {
        this.uSD = uSD;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Rates withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
