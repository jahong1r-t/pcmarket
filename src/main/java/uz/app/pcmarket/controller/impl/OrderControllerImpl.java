package uz.app.pcmarket.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.app.pcmarket.controller.OrderController;
import uz.app.pcmarket.entity.Cart;
import uz.app.pcmarket.entity.Order;
import uz.app.pcmarket.payload.req.OrderReqDTO;
import uz.app.pcmarket.repository.CartRepository;
import uz.app.pcmarket.service.OrderService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderControllerImpl implements OrderController {
    private final OrderService orderService;
    private final CartRepository cartRepository;

    @Override
    @GetMapping("/form/{userId}")
    public ModelAndView showOrderForm(@PathVariable Long userId) {
        ModelAndView mav = new ModelAndView("order-form");
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        mav.addObject("userId", userId);
        mav.addObject("cartItems", cart.getCartItems());
        mav.addObject("orderReqDTO", new OrderReqDTO());
        return mav;
    }

    @Override
    @PostMapping("/create/{userId}")
    public ModelAndView createOrder(@PathVariable Long userId, @ModelAttribute OrderReqDTO orderReqDTO) {
        Order order = orderService.createOrder(userId, orderReqDTO);
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice())
                .sum();
        ModelAndView mav = new ModelAndView("order-confirmation");
        mav.addObject("order", order);
        mav.addObject("orderReqDTO", orderReqDTO);
        mav.addObject("cartItems", cart.getCartItems());
        mav.addObject("totalPrice", totalPrice);
        return mav;
    }
}