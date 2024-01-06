package shop.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.shop.domain.Product;

@FeignClient(name = "product-service")
public interface OpenFeignClient {
    /**
     * 名称不重要,重要的是参数,请求地址,返回值
     *
     * @param pid
     * @return
     */
    @GetMapping("/product/{pid}")
    Product findById2(@PathVariable("pid") Long pid);
}
