package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kacper on 2017-03-21.
 */

@Data
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String email;

    @Column
    @OneToMany
    private List<Currency> currencies;

}
