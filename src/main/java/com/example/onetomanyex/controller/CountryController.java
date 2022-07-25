package com.example.onetomanyex.controller;

import com.example.onetomanyex.entity.City;
import com.example.onetomanyex.entity.Country;
import com.example.onetomanyex.repository.CityRepository;
import com.example.onetomanyex.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
@AllArgsConstructor
public class CountryController {

    private CountryRepository countryRepository;
    private CityRepository cityRepository;


    @PostMapping("/save")
    public Country saveCountry(@RequestBody Country country){

        return countryRepository.saveCountry(country);
    }

    @PutMapping("/update")
    public Country updateCountry(@RequestBody Country country){
        return countryRepository.updateCountry(country);
    }

    @GetMapping("/getById/{country_id}")
    public Country getCountryById(@PathVariable("country_id") Long id){
        return countryRepository.findCountryById(id);
    }

    @GetMapping("/getAll")
    public List<Country> getAllCountries(){
        return countryRepository.findAll();
    }

    @DeleteMapping("/deleteById/{country_id}")
    public Country deleteCountryById(@PathVariable("country_id") Long id){
        return countryRepository.deleteCountryById(id);
    }

    @DeleteMapping("/deleteAll")
    public int deleteAllCountries(){
        return countryRepository.deleteAll();
    }

    @GetMapping("/getCitiesByCountryId/{country_id}")
    public List<City> getCitiesByCountryId(@PathVariable("country_id") Long id){
        return countryRepository.getCitiesByCountryId(id);
    }

    @PatchMapping("/addCityToCountry/{country_id}")
    public Country addCityToCountry(@PathVariable("country_id") Long id, @RequestBody City city){
        return countryRepository.addCityToCountry(id, city);
    }

    @PatchMapping("/addCityToCountryById/{country_id}/{city_id}")
    public Country addCityToCountryById(@PathVariable("country_id") Long country_id, @PathVariable("city_id") Long city_id){
        Country country = countryRepository.findCountryById(country_id);
        City city = cityRepository.findCityById(city_id);
        country.addCity(city);
        return countryRepository.saveCountry(country);
    }
//
//    @DeleteMapping("/deleteCityFromCountry/{country_id}")
//    public Country deleteCityFromCountry(@PathVariable("country_id") Long id, @RequestBody City city){
//        return countryRepository.deleteCityFromCountry(id,city);
//    }
}
