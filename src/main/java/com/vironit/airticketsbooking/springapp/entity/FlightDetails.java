package com.vironit.airticketsbooking.springapp.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "air_ticket_booking_system.flight_details")
@Component
public class FlightDetails implements Serializable {

    private static final long serialVersionUID = 6273989404491609698L;

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_airplane", foreignKey = @ForeignKey(name = "id_airplane"))
    @Autowired
    private Airplane airplane;
    @Column
    private boolean has_transfer_point;
    @Column
    private Timestamp departure_time;
    @Column
    private Timestamp arrival_time;

    public FlightDetails(Airplane airplane, boolean has_transfer_point, Timestamp departure_time, Timestamp arrival_time) {
        this.airplane = airplane;
        this.has_transfer_point = has_transfer_point;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
    }
}
