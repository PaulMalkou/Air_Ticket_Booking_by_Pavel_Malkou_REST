package com.vironit.airticketsbooking.springapp.dao;

import java.io.Serializable;
import java.util.List;

public interface EntityDAO <PK extends Serializable, T extends Serializable> {
    void create(T entity);
    void update(T t);
    List<T> findAll(Class<T> type);
    T findById(PK id, Class<T> t);
    void delete(T entity);
}
