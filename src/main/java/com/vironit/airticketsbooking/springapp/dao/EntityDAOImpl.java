package com.vironit.airticketsbooking.springapp.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class EntityDAOImpl<PK extends Serializable, T extends Serializable> implements EntityDAO<PK, T> {
    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void create(T entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(T t) {
        getCurrentSession().update(t);
    }

    @Override
    public List<T> findAll(Class<T> type) {
        Criteria criteria = getCurrentSession().createCriteria(type);
        return criteria.list();
    }

    @Override
    public T findById(PK id, Class<T> t) {
        return getCurrentSession().get(t, id);
    }

    @Override
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

}
