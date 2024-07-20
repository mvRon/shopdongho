package com.vanlang.shopdongho.service;


import com.vanlang.shopdongho.model.CartItem;
import com.vanlang.shopdongho.model.Order;
import com.vanlang.shopdongho.model.OrderDetail;
import com.vanlang.shopdongho.repository.OrderDetailRepository;
import com.vanlang.shopdongho.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CartService cartService;

    private double total = 0;

    @Transactional
    public Order createOrder(String customerName, String address,
                             String email, String note, Long phoneNumber, String payment, List<CartItem> cartItems){
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setAddress(address);
        order.setEmail(email);
        order.setNote(note);
        order.setPhoneNumber(phoneNumber);
        order.setPayment(payment);
        order = orderRepository.save(order);

        for (CartItem item : cartItems){
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            detail.setTotal(item.getQuantity() * item.getProduct().getPrice());
            total += item.getQuantity() * item.getProduct().getPrice();
            order.setTotal(total);
            order = orderRepository.save(order);
            orderDetailRepository.save(detail);
        }


        cartService.clearCart();
        return order;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
