package shop.shop.dao;

import shop.shop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductDao extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
