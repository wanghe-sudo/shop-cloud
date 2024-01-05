package shop.shop.service;

import shop.shop.dao.ProductDao;
import shop.shop.domain.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductService {
    @Resource
    private ProductDao productDao;

    public Product findById(Long pid) {
        return productDao.findById(pid).orElseGet(null);
    }
}
