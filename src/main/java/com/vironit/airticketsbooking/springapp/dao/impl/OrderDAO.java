package com.vironit.airticketsbooking.springapp.dao.impl;

import com.vironit.airticketsbooking.springapp.dao.EntityDAOImpl;
import com.vironit.airticketsbooking.springapp.entity.Order;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAO extends EntityDAOImpl<Long, Order> {
    private static final String FIND_ORDERS_BY_USER_ID_HQL = "FROM Order o WHERE o.user.id = :idUserValue";
    private static final String FIND_ORDERS_BY_USER_EMAIL_HQL = "FROM Order o JOIN o.user WHERE o.user.email = :emailValue";
    private static final String FIND_ALL_ACTIVE_ORDERS_FOR_USER_HQL = "FROM Order order WHERE order.order_status = 'ACTIVE' AND order.user.id = :idValue";
    private static final String FIND_ALL_CANCELLED_ORDERS_FOR_USER_HQL ="FROM Order order WHERE order.order_status = 'CANCELLED' AND order.user.id = :idValue";
    private static final String FIND_ALL_FINISHED_ORDERS_FOR_USER_HQL = "FROM Order order WHERE order.order_status = 'FINISHED' AND order.user.id = :idValue";
    private static final String FIND_LAST_ORDER_SQL = "SELECT orders.id, id_user, number_passengers, id_departure_airport, id_arrival_airport, id_airline, id_flight_details, fare, is_paid, order_status FROM air_ticket_booking_system.orders WHERE id = (SELECT MAX(id) FROM air_ticket_booking_system.orders)";
    private static final String UPDATE_ORDER_STATUS_HQL = "UPDATE Order SET order_status = :orderStatusValue WHERE id = :idValue";
    private static final String UPDATE_ORDER_IS_PAID_CONDITION_HQL = "UPDATE Order SET is_paid = :isPaidValue WHERE id = :idValue";

    public OrderDAO() {
        super();
    }

    public List<Order> findOrdersByUserId(long id_user) {
        Query<Order> query = getCurrentSession().createQuery(FIND_ORDERS_BY_USER_ID_HQL);
        query.setParameter("idUserValue", id_user);
        return query.list();
    }

    public  List<Order> findOrdersByUserEmail(String email) {
        Query<Order> query = getCurrentSession().createQuery(FIND_ORDERS_BY_USER_EMAIL_HQL);
        query.setParameter("emailValue", email);
        return query.list();
    }

    public Order findOrderWithMaxId(){
        Query<Order> query = getCurrentSession().createNativeQuery(FIND_LAST_ORDER_SQL);
        return query.getSingleResult();
    }

    public List<Order> findAllUserActiveOrders(long id_user){
        return getListOrders(id_user, FIND_ALL_ACTIVE_ORDERS_FOR_USER_HQL);
    }

    public List<Order> findAllUserCancelledOrders(long id_user){
        return getListOrders(id_user, FIND_ALL_CANCELLED_ORDERS_FOR_USER_HQL);
    }

    public List<Order> findAllUserFinishedOrders(long id_user) {
        return getListOrders(id_user, FIND_ALL_FINISHED_ORDERS_FOR_USER_HQL);
    }

    public void updateOrderStatus(long id, Order.Status order_status) {
        Query query = getCurrentSession().createQuery(UPDATE_ORDER_STATUS_HQL);
        query.setParameter("idValue", id);
        query.setParameter("orderStatusValue", order_status);
        query.executeUpdate();
    }

    public void updateOrderIsPaidCondition(long id, boolean is_paid){
        Query query = getCurrentSession().createQuery(UPDATE_ORDER_IS_PAID_CONDITION_HQL);
        query.setParameter("idValue", id);
        query.setParameter("isPaidValue", is_paid);
        query.executeUpdate();
    }

    private List<Order> getListOrders(long id_user, String currentQuery) {
        Query<Order> query = getCurrentSession().createQuery(currentQuery);
        query.setParameter("idValue", id_user);
        return query.list();
    }
}
