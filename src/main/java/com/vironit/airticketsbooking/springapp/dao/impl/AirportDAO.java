package com.vironit.airticketsbooking.springapp.dao.impl;

import com.vironit.airticketsbooking.springapp.dao.EntityDAOImpl;
import com.vironit.airticketsbooking.springapp.entity.Airport;
import org.springframework.stereotype.Repository;

@Repository
public class AirportDAO extends EntityDAOImpl<Long, Airport> {
    public AirportDAO() {
        super();
    }
}
