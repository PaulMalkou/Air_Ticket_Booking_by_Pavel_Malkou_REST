package com.vironit.airticketsbooking.springapp.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "air_ticket_booking_system.airlines")
@Component
public class Airline implements Serializable {

    private static final long serialVersionUID = 6562789677472319768L;

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String code_airline;
    @Column
    private String name_airline;

}
