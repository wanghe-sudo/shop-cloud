package shop.service.impl;

import com.alibaba.fastjson2.JSON;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import shop.dao.OrderDao;
import shop.service.OrderService;
import shop.shop.domain.Order;
import shop.shop.domain.Product;

import javax.annotation.Resource;
import java.util.List;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private RestTemplate restTemplate;

    @Override
    public Order createOrder(Long productId, Long userId) {
        Product product = null;
        // 使用feign最为远程调用工具(可以使用http请求发送)
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://127.0.0.1:8081/product/" + productId, String.class);
        log.info("1 ===> ", JSON.toJSONString(forEntity.getBody()));
        List<ServiceInstance> serverList = discoveryClient.getInstances("product-service");

        String host = serverList.get(0).getHost();
        int port = serverList.get(0).getPort();
        forEntity = restTemplate.getForEntity("http://" + host + ":" + port + "/product/" + productId, String.class);
        log.info("2 ===> ", JSON.toJSONString(forEntity.getBody()));
        product = JSON.parseObject(forEntity.getBody(), Product.class);
        Order order = Order.builder().uid(userId).pid(productId)
                .pname(product.getPname())
                .number(1).build();
        orderDao.save(order);
        log.info("创建订单成功:{}", JSON.toJSONString(order));
        return order;
    }
}
