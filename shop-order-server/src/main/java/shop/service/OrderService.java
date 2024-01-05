package shop.service;

import shop.shop.domain.Order;

public interface OrderService {
    Order createOrder(Long productId, Long userId);
}
