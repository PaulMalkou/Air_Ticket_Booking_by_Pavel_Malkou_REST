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
@Table(name = "air_ticket_booking_system.airplanes")
@Component
public class Airplane implements Serializable {

    private static final long serialVersionUID = 8173018251421325700L;

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String airplane_model;

}
