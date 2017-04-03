package com.example.repository;

import com.example.entity.CurrencyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Created by Kacper on 2017-03-27.
 */
@Repository
public interface CurrencyValueRepository extends JpaRepository<CurrencyValue, Long> {

    @Query("SELECT cv FROM #{#entityName} cv INNER JOIN cv.baseCurrency b INNER JOIN cv.exchangeCurrency e WHERE b.currencyCode = ?1 AND e.currencyCode = 'EUR' AND cv.date BETWEEN ?2 AND ?3")
    List<CurrencyValue> findByCurrencyCodeAndDateBetween(String currencyCode, Date startDate, Date endDate);

    List<CurrencyValue> findByBaseCurrencyCurrencyCodeAndDateBetween(String currencyCode, Date startDate, Date endDate);
}
