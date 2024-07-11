package com.vanlang.shopdongho.controller;


import com.vanlang.shopdongho.model.CartItem;
import com.vanlang.shopdongho.service.CartService;
import com.vanlang.shopdongho.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    @GetMapping("/checkout")
    public String checkout() {
        return "cart/checkout";
    }

    @PostMapping("/submit")
    public String submitOrder(String customerName, String address, String email, String note, Long phoneNumber, String payment) {
        List<CartItem> cartItems = cartService.getCartItems();
        if (cartItems.isEmpty()) {
            return "redirect:/cart";
        }
        orderService.createOrder(customerName, address, email, note, phoneNumber, payment,cartItems);
        return "redirect:/order/confirmation";
    }

    @GetMapping("/confirmation")
    public String orderConfirmation(Model model) {
        model.addAttribute("message", "Your order has been successfully placed.");
        return "cart/order-confirmation";
    }

    @GetMapping("/customer")
    public String showOrders(Model model){
        model.addAttribute("orders", orderService.getAllOrders());
        return "redirect:/order/customer";
    }


}



