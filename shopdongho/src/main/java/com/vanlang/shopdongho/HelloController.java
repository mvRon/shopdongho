package com.vanlang.shopdongho;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping()
    public String homepage() {
        return "Hello Worlds from controller";  // Trả về trang index.html
    }


}
