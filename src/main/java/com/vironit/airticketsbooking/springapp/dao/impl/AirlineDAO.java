package com.vironit.airticketsbooking.springapp.dao.impl;

import com.vironit.airticketsbooking.springapp.dao.EntityDAOImpl;
import com.vironit.airticketsbooking.springapp.entity.Airline;
import org.springframework.stereotype.Repository;

@Repository
public class AirlineDAO extends EntityDAOImpl<Long, Airline> {
    public AirlineDAO() {
        super();
    }
}
