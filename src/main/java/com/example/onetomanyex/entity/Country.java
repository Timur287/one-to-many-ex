package com.example.onetomanyex.entity;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String countryName;

    @NotNull
    Long population;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "country")
    List<City> cities;


    public void addCity(City city){
        cities.add(city);
        city.setCountry(this);
    }

    public void removeCity(City city){
        cities.remove(city);
        city.setCountry(null);
    }

    public Country(){
        cities = new ArrayList<>();
    }
}
