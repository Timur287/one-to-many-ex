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
public class CityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public City saveCity(City city){
        entityManager.persist(city);
        return city;
    }

    @Transactional
    public City updateCity(City city){
        return entityManager.merge(city);
    }

    public City findCityById(Long id){
        return entityManager.find(City.class,id);
    }

    public List<City> findAll(){
        TypedQuery<City> typedQuery = entityManager.createQuery("select c from City c", City.class);
        return typedQuery.getResultList();
    }

    @Transactional
    public City deleteCityById(Long id){
        City city = findCityById(id);
        entityManager.remove(city);
        return city;
    }

    @Transactional
    public int deleteAll() {
        Query query = entityManager.createQuery("delete from City");
        return query.executeUpdate();
    }
}
