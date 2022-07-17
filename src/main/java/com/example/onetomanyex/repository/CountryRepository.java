package com.example.onetomanyex.repository;

import com.example.onetomanyex.entity.City;
import com.example.onetomanyex.entity.Country;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CountryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Country saveCountry(Country country){
        entityManager.persist(country);
        return country;
    }

    @Transactional
    public Country updateCountry(Country country){
        return entityManager.merge(country);
    }

    public Country findCountryById(Long id){
        return entityManager.find(Country.class,id);
    }

    public List<Country> findAll(){
        TypedQuery<Country> typedQuery = entityManager.createQuery("select c from Country c", Country.class);
        return typedQuery.getResultList();
    }

    @Transactional
    public Country deleteCountryById(Long id){
        Country country = findCountryById(id);
        entityManager.remove(country);
        return country;
    }

    @Transactional
    public int deleteAll(){
        Query query = entityManager.createQuery("delete from Country");
        return query.executeUpdate();
    }


    public List<City> getCitiesByCountryId(Long id){
        Country country = findCountryById(id);
        return country.getCities();
    }

}
