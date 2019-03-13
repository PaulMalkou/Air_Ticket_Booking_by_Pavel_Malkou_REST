package com.vironit.airticketsbooking.springapp.service;

import com.vironit.airticketsbooking.springapp.dao.impl.AirlineDAO;
import com.vironit.airticketsbooking.springapp.entity.Airline;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class AirlineService {
    private AirlineDAO dao;

    @Autowired
    public AirlineService(AirlineDAO dao) {
        this.dao = dao;
    }

    public List<Airline> findAllAirlines() {
        List<Airline> airlines = dao.findAll(Airline.class);
        return airlines;
    }

    public Airline findAirlineById(long id) {
        Airline airline = dao.findById(id, Airline.class);
        return airline;
    }
}
