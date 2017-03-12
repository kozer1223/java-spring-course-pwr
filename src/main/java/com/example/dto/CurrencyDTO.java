package com.example.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by Kacper on 2017-03-09.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CurrencyDTO {

    private BigDecimal value;
    private Currency currency;

}
