package com.vironit.airticketsbooking.springapp.controller;

import com.vironit.airticketsbooking.springapp.entity.*;
import com.vironit.airticketsbooking.springapp.exception.NotEnoughMoneyException;
import com.vironit.airticketsbooking.springapp.exception.OrderAlreadyPaidException;
import com.vironit.airticketsbooking.springapp.exception.OrderIsNotActiveException;
import com.vironit.airticketsbooking.springapp.exception.handling.ValidationErrorBuilder;
import com.vironit.airticketsbooking.springapp.service.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class OrderController {
    private final static Logger LOGGER = LogManager.getLogger(OrderController.class);
    private OrderService orderService;
    private UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping(value = "/order/",  produces = "application/json")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        if (orders == null) {
            LOGGER.error("Orders are not found");
            throw new EntityNotFoundException("Orders not found");
        }
        LOGGER.info("Get all orders");
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping(value = "/order/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> createOrder(@Validated @RequestBody Order order, BindingResult result) {
        if (result.hasErrors()) {
            LOGGER.info("There are problems with validation of fields during placing a new order");
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(result));
        }
        User user = userService.findUserById(order.getUser().getId());
        if (user == null) {
            LOGGER.error("User with not found.");
            throw new EntityNotFoundException("User not found");
        }
        orderService.createOrder(order);
        LOGGER.log(Level.INFO, "Order with ID: " + order.getId() + " is created");
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping(value = "/order/{id}", produces = "application/json")
    public ResponseEntity<Order> getOrder(@PathVariable("id")long id) {
        Order order = orderService.findOrderById(id);
        if (order == null) {
            LOGGER.error("Order with id {} not found.", id);
            throw new EntityNotFoundException("Order not found");
        }
        LOGGER.info("Get Order with id {}", id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping(value = "/order/{id}", produces = "application/json")
    public ResponseEntity<Order> updateOrderMakePaid(@PathVariable("id")long id) throws OrderAlreadyPaidException,
                                                                                        NotEnoughMoneyException {
        LOGGER.info("Paying order with id {}", id);
        Order order = orderService.findOrderById(id);
        if (order == null) {
            LOGGER.error("Unable to pay. Order with id {} not found.", id);
            throw new EntityNotFoundException("Order not found");
        }
        if (order.is_paid() == true) {
            LOGGER.error("Unable to pay. Order with id {} is already paid", id);
            throw new OrderAlreadyPaidException("Order is already paid");
        }
        User user = order.getUser();
        if(!userService.checkBalance(user, order.getFare())){
            LOGGER.error("Unable to pay. User with id {} don't have enough money", user.getId());
            throw new NotEnoughMoneyException("User don't have enough money");
        }
        orderService.pickUpOrder(id);
        userService.withdrawnMoney(user, order.getFare());
        LOGGER.log(Level.INFO, "Order with ID:" + order.getId() + " is paid");
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping(value = "/order/{id}/make-order-сancelled", produces = "application/json")
    public ResponseEntity<Order> updateOrderMakeCancelled(@PathVariable("id")long id) throws OrderIsNotActiveException {
        Order order = orderService.findOrderById(id);
        if (order == null) {
            LOGGER.error("Unable to cancel. Order with id {} not found.", id);
            throw new EntityNotFoundException("Order not found");
        }
        if (!order.getOrder_status().equals(Order.Status.ACTIVE)) {
            LOGGER.error("Unable to cancel. Order with id {} not found or it's status is not ACTIVE", id);
            throw new OrderIsNotActiveException("Order is not ACTIVE");
        }
        orderService.cancelOrder(id);
        LOGGER.log(Level.INFO, "Order with ID:" + id + " is сancelled");
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping(value = "/order/{id}/make-order-finished", produces = "application/json")
    public ResponseEntity<Order> updateOrderMakeFinished(@PathVariable("id")long id) throws OrderIsNotActiveException {
        Order order = orderService.findOrderById(id);
        if (order == null) {
            LOGGER.error("Unable to finish. Order with id {} not found.", id);
            throw new EntityNotFoundException("Order not found");
        }
        if (!order.getOrder_status().equals(Order.Status.ACTIVE)) {
            LOGGER.error("Unable to finish. Order with id {} not found or it's status is not ACTIVE", id);
            throw new OrderIsNotActiveException("Order is not ACTIVE");
        }
        orderService.finishOrder(id);
        LOGGER.log(Level.INFO, "Order with ID:" + id + " is finished");
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping(value = "/order/{id}", produces = "application/json")
    public ResponseEntity<Order> deleteOrder(@PathVariable("id")long id) {
        Order order = orderService.findOrderById(id);
        if (order == null) {
            LOGGER.error("Unable to delete. Order with id {} not found.", id);
            throw new EntityNotFoundException("Order not found");
        }
        orderService.deleteOrder(order);
        LOGGER.info("Order with id {} is removed", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/user/{id}/orders/", produces = "application/json")
    public ResponseEntity<List<Order>> getOrdersOfUser(@PathVariable("id")long id) {
        checkIsUserExist(id);
        List<Order> orders = orderService.findOrdersByUserId(id);
        if (orders == null) {
            LOGGER.error("Unable to find. Orders for user with id {} not found.", id);
            throw new EntityNotFoundException("Orders not found");
        }
        LOGGER.info("Get orders for user with id {}", id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}/orders/order_status=active", produces = "application/json")
    public ResponseEntity<List<Order>> getActiveOrdersOfUser(@PathVariable("id")long id) {
        checkIsUserExist(id);
        List<Order> orders = orderService.findAllUserActiveOrders(id);
        if (orders == null) {
            LOGGER.error("Unable to find. Active orders for user with id {} is not found.", id);
            throw new EntityNotFoundException("Active orders for user is not found");
        }
        LOGGER.info("Get active orders for user with id {}", id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}/orders/order_status=cancelled", produces = "application/json")
    public ResponseEntity<List<Order>> getCancelledOrdersOfUser(@PathVariable("id")long id) {
        checkIsUserExist(id);
        List<Order> orders = orderService.findAllUserCancelledOrders(id);
        if (orders == null) {
            LOGGER.error("Unable to find. Cancelled orders for user with id {} is not found.", id);
            throw new EntityNotFoundException("Cancelled orders for user is not found");
        }
        LOGGER.info("Get cancelled orders for user with id {}", id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}/orders/order_status=finished", produces = "application/json")
    public ResponseEntity<List<Order>> getFinishedOrdersOfUser(@PathVariable("id")long id) {
        checkIsUserExist(id);
        List<Order> orders = orderService.findAllUserFinishedOrders(id);
        if (orders == null) {
            LOGGER.error("Unable to find. Finished orders for user with id {} not found.", id);
            throw new EntityNotFoundException("Finished orders for user is not found");
        }
        LOGGER.info("Get finished orders for user with id {}", id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    private void checkIsUserExist(Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            LOGGER.error("User is not found.");
            throw new EntityNotFoundException("User not found");
        }
    }

}
