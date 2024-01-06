package shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderServer {
    public static void main(String[] args) {
        SpringApplication.run(OrderServer.class, args);
    }

    @Bean
    /**
     * 集成Ribbon负载均衡
     * @LoadBalanced注解的RestTemplate获取具体的服务IP地址，是通过拦截器的方式实现的。
     * 拦截器是通过LoadBalancerClient接口实现的。LoadBalancerClient接口提供了获取服务实例的方法。当RestTemplate发起HTTP请求时，拦截器会调用LoadBalancerClient接口获取服务实例，然后从服务实例中获取到IP地址。
     * LoadBalancerInterceptor.intercept()
     */
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
