package com.vironit.airticketsbooking.springapp.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "air_ticket_booking_system.orders")
@Component
public class Order implements Serializable {

    private static final long serialVersionUID = 7789768625645788302L;

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "id_user"))
    @Autowired
    @JsonBackReference
    private User user;
    @Column
    @Min(value = 1, message = "There are at least 1 passenger")
    @Max(value = 10, message = "There are no more 10 passengers")
    private int number_passengers;
    @ManyToOne
    @JoinColumn(name = "id_departure_airport", foreignKey = @ForeignKey(name = "id_departure_airport"))
    @Autowired
    private Airport departure_airport;
    @ManyToOne
    @JoinColumn(name = "id_arrival_airport", foreignKey = @ForeignKey(name = "id_arrival_airport"))
    @Autowired
    private Airport arrival_airport;
    @ManyToOne
    @JoinColumn(name = "id_airline", foreignKey = @ForeignKey(name = "id_airline"))
    @Autowired
    private Airline airline;
    @ManyToOne
    @JoinColumn(name = "id_flight_details", foreignKey = @ForeignKey(name = "id_flight_details"))
    @Autowired
    private FlightDetails flightDetails;
    @Column
    @Min(value = 50, message = "Fare should not be less than 50")
    @Max(value = 550, message = "Fare should not be more than 550")
    private double fare;
    @Column
    private boolean is_paid;
    @Enumerated(EnumType.STRING)
    @Column
    private Order.Status order_status;

    public Order(User user, int number_passengers, Airport departure_airport, Airport arrival_airport, Airline airline,
                 FlightDetails flightDetails, double fare, boolean is_paid, Order.Status status)
    {
        this.user = user;
        this.number_passengers = number_passengers;
        this.departure_airport = departure_airport;
        this.arrival_airport = arrival_airport;
        this.airline = airline;
        this.flightDetails = flightDetails;
        this.fare = fare;
        this.is_paid = is_paid;
        this.order_status = status;
    }

    public enum Status {
        ACTIVE, CANCELLED, FINISHED
    }

}




