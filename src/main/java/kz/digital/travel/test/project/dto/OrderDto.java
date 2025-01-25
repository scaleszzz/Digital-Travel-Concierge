package kz.digital.travel.test.project.dto;

import kz.digital.travel.test.project.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long orderId;
    private String customerName;
    private OrderStatus orderStatus;
    private double totalPrice;
    private List<ProductDto> product;
}
