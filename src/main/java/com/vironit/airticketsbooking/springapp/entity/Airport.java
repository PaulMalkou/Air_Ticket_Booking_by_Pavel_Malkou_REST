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
@Table(name = "air_ticket_booking_system.airports")
@Component
public class Airport implements Serializable {

    private static final long serialVersionUID = 6488115908233047680L;

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private String country;
    @Column
    private String city;

}
