package com.vironit.airticketsbooking.springapp.dao.impl;

import com.vironit.airticketsbooking.springapp.dao.EntityDAOImpl;
import com.vironit.airticketsbooking.springapp.entity.FlightDetails;
import org.springframework.stereotype.Repository;

@Repository
public class FlightDetailsDAO extends EntityDAOImpl<Long, FlightDetails> {
    public FlightDetailsDAO() {
        super();
    }
}
