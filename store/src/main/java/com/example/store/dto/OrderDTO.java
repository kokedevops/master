package com.example.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    
    private Long id;
    
    @NotBlank(message="Client required")
    private String clientId;
    
    @Size(min=1)
    private List<ProductDTO> products;
    
}
