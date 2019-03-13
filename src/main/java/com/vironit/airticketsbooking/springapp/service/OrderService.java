package com.vironit.airticketsbooking.springapp.service;

import com.vironit.airticketsbooking.springapp.dao.impl.OrderDAO;
import com.vironit.airticketsbooking.springapp.entity.FlightDetails;
import com.vironit.airticketsbooking.springapp.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;

@Transactional
@Service
public class OrderService {
    private OrderDAO dao;
    FlightDetailsService flightDetailsService;

    @Autowired
    public OrderService(OrderDAO dao, FlightDetailsService flightDetailsService) {
        this.dao = dao;
        this.flightDetailsService = flightDetailsService;
    }

    public void createOrder(Order order) {
        FlightDetails flightDetails = flightDetailsService.findFlightDetailsById(getFlightDetailsID());
        order.setFlightDetails(flightDetails);
        double fare = setRandomFare();
        order.setFare(fare);
        order.setOrder_status(Order.Status.ACTIVE);
        order.set_paid(false);
        dao.create(order);
    }

    public List<Order> findAllOrders() {
        List<Order> orders = dao.findAll(Order.class);
        return orders;
    }

    public Order findOrderById(long id) {
        Order order = dao.findById(id, Order.class);
        return order;
    }

    public List<Order> findOrdersByUserId(long user_id) {
        List<Order> orders = dao.findOrdersByUserId(user_id);
        return orders;

    }

    public List<Order> findOrdersByUserEmail(String email) {
        List<Order> orders = dao.findOrdersByUserEmail(email);
        return orders;
    }

    public Order findLastOrder() {
        Order order = dao.findOrderWithMaxId();
        return order;
    }

    public List<Order> findAllUserActiveOrders(long id_user) {
        List<Order> orders = dao.findAllUserActiveOrders(id_user);
        return orders;
    }

    public List<Order> findAllUserCancelledOrders(long id_user) {
        List<Order> orders = dao.findAllUserCancelledOrders(id_user);
        return orders;
    }

    public List<Order> findAllUserFinishedOrders(long id_user) {
        List<Order> orders = dao.findAllUserFinishedOrders(id_user);
        return orders;
    }

    public boolean isOrderActive(long id) {
        Order order = dao.findById(id, Order.class);
        return order.getOrder_status() == Order.Status.ACTIVE;
    }

    public void cancelOrder(long id) {
        dao.updateOrderStatus(id, Order.Status.CANCELLED);
    }

    public void finishOrder(long id) {
        dao.updateOrderStatus(id, Order.Status.FINISHED);
    }

    public void pickUpOrder(long id) {
        dao.updateOrderIsPaidCondition(id, true);
    }

    public void deleteOrder(Order order) {
        dao.delete(order);
    }

    private double setRandomFare() {
        Random random = new Random();
        int fare = random.nextInt(500) + 50;
        return fare;
    }

    private long getFlightDetailsID() {
        long idFlightDetails =  new Random().nextInt(5) + 1;
        return idFlightDetails;
    }

}
