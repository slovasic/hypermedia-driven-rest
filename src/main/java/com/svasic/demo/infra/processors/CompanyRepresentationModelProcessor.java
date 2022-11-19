package com.svasic.demo.infra.processors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.svasic.demo.api.CompanyController;
import com.svasic.demo.api.ProductController;
import com.svasic.demo.domain.company.view.CompanyDto;

/**
 * Processes model by adding links to Product(s)
 * 
 * @author Slobodan Vasic
 *
 */
@Component
public class CompanyRepresentationModelProcessor
		implements RepresentationModelProcessor<CompanyDto> {

	@Override
	public CompanyDto process(CompanyDto model) {

		model.add(linkTo(methodOn(CompanyController.class).company(model.getId()))
				.withSelfRel());

		model.getProducts().stream()
				.forEach(product -> model.add(
						linkTo(methodOn(ProductController.class).product(product.getId()))
								.withRel("products")));

		return model;
	}

}
