package shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import shop.shop.domain.Order;

public interface OrderDao extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
}
