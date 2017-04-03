package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Kacper on 2017-03-21.
 */
@Data
@Entity
@Table(name="currency")
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyData {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    String currencyCode;

    @Column
    String name;
}
