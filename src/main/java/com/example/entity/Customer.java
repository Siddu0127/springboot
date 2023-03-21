package com.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name="Customer")
@Data

@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id

    private int id;
    @Column(name = "customerName")
    @NotNull
    @Pattern(regexp = "[a-zA-Z]+",message ="Employee name must be in Alphabet" )
    private String customerName;
    @NotNull
    @Email(message = "Please enter a valid email Id", regexp="^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")

    private String customerEmail;
    @Pattern(regexp = "^\\d{10}$",message = "Employee phoneno is not valid ")
    @NotNull
    private String customerPhone;



    public Customer(int id, String customerName, String customerEmail, String customerPhone) {
        this.id = id;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
    }


}
