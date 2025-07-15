package uz.app.pcmarket.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.app.pcmarket.entity.Order;
import uz.app.pcmarket.payload.req.OrderReqDTO;

@RequestMapping("/order")
public interface OrderController {
    @GetMapping("/form/{userId}")
    ModelAndView showOrderForm(@PathVariable Long userId);

    @PostMapping("/create/{userId}")
    ModelAndView createOrder(@PathVariable Long userId, @ModelAttribute OrderReqDTO orderReqDTO);
}
