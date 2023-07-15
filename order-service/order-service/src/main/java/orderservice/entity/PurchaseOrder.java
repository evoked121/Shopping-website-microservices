package orderservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;
import orderservice.dto.OrderStatus;

@Entity
@Data
@ToString
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private Integer id;
    private String productId;
    private Integer userId;
    private Integer amount;
    private OrderStatus status;

}
