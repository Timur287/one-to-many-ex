package com.example.onetomanyex.controller;

import com.example.onetomanyex.entity.City;
import com.example.onetomanyex.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/city")
public class CityController {

    private CityRepository cityRepository;


    @PostMapping("/save")
    public City saveCity(@RequestBody City city){
        return cityRepository.saveCity(city);
    }

    @PutMapping("/update")
    public City updateCity(@RequestBody City city){
        return cityRepository.updateCity(city);
    }

    @GetMapping("/getById/{city_id}")
    public City getCityById(@PathVariable("city_id") Long id){
        return cityRepository.findCityById(id);
    }

    @GetMapping("/getAll")
    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

    @DeleteMapping("/deleteById/{city_id}")
    public City deleteCityById(@PathVariable("city_id") Long id){
        return cityRepository.deleteCityById(id);
    }

    @DeleteMapping("/deleteAll")
    public int deleteAllCities(){
        return cityRepository.deleteAll();
    }
}
