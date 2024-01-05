package shop.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "t_shop_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;
    private Long uid;
    private String username;
    /**
     * 商品id
     */
    private Long pid;
    /**
     * 商品名称
     */
    private String pname;
    /**
     * 商品单价
     */
    private BigDecimal pprice;
    private Integer number;
}
