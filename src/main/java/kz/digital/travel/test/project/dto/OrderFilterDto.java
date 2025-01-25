package kz.digital.travel.test.project.dto;

import kz.digital.travel.test.project.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderFilterDto {
    private OrderStatus status;
    private Double minPrice;
    private Double maxPrice;
}
