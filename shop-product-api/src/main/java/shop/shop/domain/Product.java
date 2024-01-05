package shop.shop.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity(name = "t_shop_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;
    /**
     * 名称
     */
    private String pname;
    /**
     * 单价
     */
    private BigDecimal pprice;
    /**
     * 库存
     */
    private Integer stock;
}
