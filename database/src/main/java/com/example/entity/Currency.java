package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Kacper on 2017-03-21.
 */
@Data
@Entity
@Table(name="currency")
public class Currency {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    String currencyName;

    @Column
    BigDecimal value;
}
