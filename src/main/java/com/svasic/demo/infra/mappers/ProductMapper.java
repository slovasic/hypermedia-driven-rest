package com.svasic.demo.infra.mappers;

import org.mapstruct.Mapper;

import com.svasic.demo.domain.product.model.Product;
import com.svasic.demo.domain.product.view.ProductDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	ProductDto productToDto(Product product);
}
