package com.svasic.demo.infra.processors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Set;

import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.svasic.demo.domain.company.api.CompanyController;
import com.svasic.demo.domain.company.view.CompanyDto;
import com.svasic.demo.domain.product.api.ProductController;
import com.svasic.demo.domain.product.view.ProductDto;

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

		for (ProductDto product : model.getProducts()) {
			model.add(linkTo(methodOn(ProductController.class).product(product.getId()))
					.withRel("products"));
		}

		return model;
	}

}
