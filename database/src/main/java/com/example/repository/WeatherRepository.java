package com.example.repository;

import com.example.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Kacper on 2017-03-21.
 */
@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
}
