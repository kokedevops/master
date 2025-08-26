package com.example.store.mapper;

import com.example.store.dto.OrderDTO;
import com.example.store.dto.ProductDTO;
import com.example.store.model.Order;
import com.example.store.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface OrderMapper {

    @Mapping(source="products", target="products")
    OrderDTO orderToOrderDTO(Order order); //Order -> OrderDTO

    @Mapping(source="category.categoryName", target="categoryName")
    ProductDTO productToProductDTO(Product product);

    List<ProductDTO> productsToProductDTOs(List<Product> products);
    
    List<OrderDTO> ordersToOrderDTOs(List<Order> orders);
    
    @InheritInverseConfiguration
    Order orderDTOToOrder(OrderDTO orderDTO);

}
