package com.example.model;

import lombok.Data;

import java.sql.Date;

/**
 * Created by Kacper on 2017-03-27.
 */
@Data
public class CurrencyStatParams {

    String currency;
    Date startDate;
    Date endDate;

}
