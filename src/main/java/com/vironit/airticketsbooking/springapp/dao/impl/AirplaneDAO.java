package com.vironit.airticketsbooking.springapp.dao.impl;

import com.vironit.airticketsbooking.springapp.dao.EntityDAOImpl;
import com.vironit.airticketsbooking.springapp.entity.Airplane;
import org.springframework.stereotype.Repository;

@Repository
public class AirplaneDAO extends EntityDAOImpl<Long, Airplane> {
    public AirplaneDAO() {
        super();
    }
}
