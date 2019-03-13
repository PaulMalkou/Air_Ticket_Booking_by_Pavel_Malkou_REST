package com.vironit.airticketsbooking.springapp.service;

import com.vironit.airticketsbooking.springapp.dao.impl.AirportDAO;
import com.vironit.airticketsbooking.springapp.entity.Airport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class AirportService {
    private AirportDAO dao;

    @Autowired
    public AirportService(AirportDAO dao) {
        this.dao = dao;
    }

    public List<Airport> findAllAirports() {
            List<Airport> airports = dao.findAll(Airport.class);
            return airports;
        }

    public Airport findAirportById(long id) {
        Airport airport = dao.findById(id, Airport.class);
        return airport;
    }

}
