package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Kacper on 2017-03-27.
 */
@Data
@Entity
@Table(name="currency_value")
public class CurrencyValue {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    CurrencyData baseCurrency;

    @OneToOne
    CurrencyData exchangeCurrency;

    @Column
    BigDecimal value;

    @Column
    Date date;

}
