package com.vironit.airticketsbooking.springapp.service;

import com.vironit.airticketsbooking.springapp.dao.impl.FlightDetailsDAO;
import com.vironit.airticketsbooking.springapp.entity.FlightDetails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class FlightDetailsService {
    private FlightDetailsDAO dao;

    @Autowired
    public FlightDetailsService(FlightDetailsDAO dao) {
        this.dao = dao;
    }

    public List<FlightDetails> findAllFlightDetails() {
        List<FlightDetails> flightDetails = dao.findAll(FlightDetails.class);
        return flightDetails;
    }

    public FlightDetails findFlightDetailsById(Long id) {
        FlightDetails flightDetails = dao.findById(id, FlightDetails.class);
        return flightDetails;
    }
}
