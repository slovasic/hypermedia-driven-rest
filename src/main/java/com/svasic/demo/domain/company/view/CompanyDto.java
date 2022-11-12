package com.svasic.demo.domain.company.view;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.svasic.demo.domain.product.view.ProductDto;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class CompanyDto extends RepresentationModel<CompanyDto> {

	private final long id;
	private final String name;
	@JsonInclude(value = Include.NON_NULL)
	private final Set<ProductDto> products;

}