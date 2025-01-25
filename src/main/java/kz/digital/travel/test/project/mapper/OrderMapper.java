package kz.digital.travel.test.project.mapper;

import kz.digital.travel.test.project.dto.OrderDto;
import kz.digital.travel.test.project.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto toDto(Order order);

    Order toEntity(OrderDto orderDto);
}
