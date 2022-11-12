package com.svasic.demo.domain.product.view;

import org.springframework.hateoas.RepresentationModel;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class ProductDto extends RepresentationModel<ProductDto> {
	private final long id;
	private final String name;
}
