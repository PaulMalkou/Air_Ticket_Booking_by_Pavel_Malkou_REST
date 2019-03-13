package com.vironit.airticketsbooking.springapp.service;

import com.vironit.airticketsbooking.springapp.dao.impl.AirplaneDAO;
import com.vironit.airticketsbooking.springapp.entity.Airplane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class AirplaneService {
    private AirplaneDAO dao;

    @Autowired
    public AirplaneService(AirplaneDAO dao) {
        this.dao = dao;
    }

    public List<Airplane> findAllAirplanes() {
        List<Airplane> airplanes = dao.findAll(Airplane.class);
        return airplanes;
    }

    public Airplane findAirplaneById(long id) {
        Airplane airplane = dao.findById(id, Airplane.class);
        return airplane;
    }
}
