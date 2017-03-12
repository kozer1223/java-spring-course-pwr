package com.example.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by Kacper on 2017-03-09.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class CurrencyDTO {

    private BigDecimal value;
    private Currency currency;

}
