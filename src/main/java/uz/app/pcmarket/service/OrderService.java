package uz.app.pcmarket.service;

import uz.app.pcmarket.entity.Order;
import uz.app.pcmarket.payload.req.OrderReqDTO;

public interface OrderService {
    Order createOrder(Long userId, OrderReqDTO orderReqDTO);
    Order getOrderById(Long orderId);
}
