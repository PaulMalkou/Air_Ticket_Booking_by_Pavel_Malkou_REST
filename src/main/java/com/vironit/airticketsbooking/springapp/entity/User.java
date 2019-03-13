package com.vironit.airticketsbooking.springapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"ordersOfUser"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "air_ticket_booking_system.users")
@Component
public class User implements Serializable {

    private static final long serialVersionUID = 2777593605973065364L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @Pattern(regexp = "[a-z0-9._%+-]{1,20}@[a-z0-9.-]{1,16}\\.[a-z]{2,3}$",
             message = "Enter correct email")
    private String email;
    @Column
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,100}",
            message = "Password must have 6+ chars (at least one number, one upper and lower case letter, max 100 chars)")
    private String password;
    @Column
    @NotBlank(message = "Name can't be blank!")
    @Size(max = 50, message = "Name must be between 1 and 50 characters")
    private String name;
    @Column
    @NotBlank(message = "Surname can't be blank!")
    @Size(max = 50, message = "Name must be between 1 and 50 characters")
    private String surname;
    @Column
    @Size(min = 10, max = 20, message = "Passport number must be between 10 and 20 characters")
    private String passport_number;
    @Column
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private Date dob;
    @Column
    @Size(min = 1, max = 1, message = "Field 'Sex' must have 'M' or 'F' value")
    private String sex;
    @Column
    @Pattern(regexp = "[+]\\d{3}[(]\\d{2}[)]\\d{3}[-]\\d{2}[-]\\d{2}",
             message = "Enter phone number in correct format: +375(29)777-77-77")
    private String phone_number;
    @Enumerated(EnumType.STRING)
    @Column
    private Role role;
    @Column
    @Min(value = 10, message = "Balance should not be less than 10")
    @Max(value = 1000, message = "Balance should not be more than 1000")
    private double balance;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Order> ordersOfUser;

    public User(String email, String password, String name, String surname, String passport_number,
                Date dob, String sex, String phone_number, Role role, double balance)
    {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.passport_number = passport_number;
        this.dob = dob;
        this.sex = sex;
        this.phone_number = phone_number;
        this.role = role;
        this.balance = balance;
    }

    public enum Role {
        ADMIN, USER
    }

}
