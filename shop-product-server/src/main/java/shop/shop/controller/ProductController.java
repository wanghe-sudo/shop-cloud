package shop.shop.controller;

import com.alibaba.fastjson2.JSON;
import shop.shop.domain.Product;
import shop.shop.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/product")
@Log4j2
public class ProductController {
    @Resource
    private ProductService productService;

    @GetMapping("/{pid}")
    public Product findById(@PathVariable("pid") Long pid) {
        Product byId = productService.findById(pid);
        log.info("查询的商品:{}", JSON.toJSONString(byId));
        return byId;
    }
}
