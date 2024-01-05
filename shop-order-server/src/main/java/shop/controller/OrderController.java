package shop.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.service.OrderService;
import shop.shop.domain.Order;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/save")
    public Order order(@RequestParam Long pid, @RequestParam Long userId) {
        return orderService.createOrder(pid, userId);
    }
}
