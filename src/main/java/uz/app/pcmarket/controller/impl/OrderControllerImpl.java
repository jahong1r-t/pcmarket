package uz.app.pcmarket.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.app.pcmarket.entity.Cart;
import uz.app.pcmarket.entity.Order;
import uz.app.pcmarket.entity.Product;
import uz.app.pcmarket.entity.enums.OrderStatus;
import uz.app.pcmarket.payload.req.OrderReqDTO;
import uz.app.pcmarket.payload.resp.OrderRespDTO;
import uz.app.pcmarket.repository.CartRepository;
import uz.app.pcmarket.repository.OrderRepository;
import uz.app.pcmarket.repository.ProductRepository;
import uz.app.pcmarket.service.OrderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderControllerImpl {
    private final OrderService orderService;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @GetMapping
    public String index(Model model) {
        List<Order> all = orderRepository.findAll();

        List<OrderRespDTO> list = all.stream().map(o -> OrderRespDTO.builder()
                .id(o.getId())
                .productName(o.getProduct().getName())
                .productImage(o.getProduct().getImage().getId())
                .productCount(o.getQuantity().toString())
                .totalPrice(o.getProduct().getPrice() * o.getQuantity())
                .orderDate(o.getCreatedAt().toLocalDate())
                .userName(o.getUser().getFullName())
                .userPhone(o.getUser().getPhoneNumber())
                .status(o.getStatus().name())
                .build()
        ).toList();

        model.addAttribute("orders", list);

        return "/admin/order-manage";
    }

    @GetMapping("/update-status/{orderId}/{status}")
    public String updateOrderStatus(@PathVariable Long orderId, @PathVariable String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderStatus currentStatus = order.getStatus();
        OrderStatus newStatus = OrderStatus.valueOf(status);

        if (currentStatus == OrderStatus.CANCELED) {
            throw new IllegalStateException("CANCELED orderni boshqa statusga o'zgartirib bo'lmaydi.");
        }

        if (currentStatus == OrderStatus.ACTIVE && newStatus == OrderStatus.CANCELED) {
            throw new IllegalStateException("ACTIVE orderni to'g'ridan-to'g'ri CANCELED qilish mumkin emas.");
        }

        if (newStatus == OrderStatus.SOLD && currentStatus != OrderStatus.SOLD) {
            Product product = productRepository.findById(order.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            int remaining = product.getQuantity() - order.getQuantity();
            if (remaining < 0) {
                throw new IllegalStateException("Yetarli mahsulot yo'q.");
            }

            product.setQuantity(remaining);
            productRepository.save(product);
        }

        if (currentStatus == OrderStatus.SOLD && newStatus == OrderStatus.CANCELED) {
            Product product = productRepository.findById(order.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            product.setQuantity(product.getQuantity() + order.getQuantity());
            productRepository.save(product);
        }

        order.setStatus(newStatus);
        orderRepository.save(order);

        return "redirect:/orders";
    }

    @GetMapping("/delete/{orderId}")
    public String deleteOrder(@PathVariable Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        orderRepository.delete(order);
        return "redirect:/orders";
    }

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

//    @GetMapping
//    public String orderPage(){
//        return "order-form";
//    }


   /* @GetMapping("/create/{userId}")
    public  String showCreateOrderPage(@PathVariable Long userId) {
        return "order-form";
    }*/
}