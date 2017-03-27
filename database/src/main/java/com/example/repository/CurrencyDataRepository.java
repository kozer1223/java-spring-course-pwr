package com.example.repository;

import com.example.entity.CurrencyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Kacper on 2017-03-27.
 */
@Repository
public interface CurrencyDataRepository extends JpaRepository<CurrencyData, Long> {
    CurrencyData findByCurrencyCode(String currencyCode);
}
