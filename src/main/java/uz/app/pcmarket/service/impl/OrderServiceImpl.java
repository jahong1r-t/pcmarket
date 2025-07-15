package uz.app.pcmarket.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.app.pcmarket.entity.Cart;
import uz.app.pcmarket.entity.Order;
import uz.app.pcmarket.entity.User;
import uz.app.pcmarket.entity.enums.OrderStatus;
import uz.app.pcmarket.payload.req.OrderReqDTO;
import uz.app.pcmarket.repository.CartRepository;
import uz.app.pcmarket.repository.OrderRepository;
import uz.app.pcmarket.repository.UserRepository;
import uz.app.pcmarket.service.OrderService;

@Service("orderService")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @Override
    public Order createOrder(Long userId, OrderReqDTO orderReqDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Order order = Order.builder()
                .status(OrderStatus.ACTIVE)
                .user(user)
                .build();

        orderRepository.save(order);
        return order;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}