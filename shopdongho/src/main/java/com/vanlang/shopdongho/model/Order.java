package com.vanlang.shopdongho.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String address;
    private Long phoneNumber;
    private String note;
    private String email;
    private String payment;
    private double total;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

}
