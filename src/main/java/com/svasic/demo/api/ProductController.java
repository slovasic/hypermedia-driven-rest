package com.svasic.demo.api;

import static com.svasic.demo.config.ApplicationUrls.REST_API_PRODUCTS_V1;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.TypedEntityLinks;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svasic.demo.domain.product.view.ProductDto;
import com.svasic.demo.services.ProductService;

@RestController
@ExposesResourceFor(ProductDto.class)
@RequestMapping(value = REST_API_PRODUCTS_V1)
public class ProductController {

	private final ProductService productService;

	private final TypedEntityLinks<ProductDto> productLinks;

	public ProductController(ProductService productService, EntityLinks entityLinks) {

		this.productService = productService;
		this.productLinks = entityLinks.forType(ProductDto::getId);
	}

	@GetMapping("{id}")
	public EntityModel<ProductDto> product(@PathVariable("id") long id) {

		ProductDto productDto = productService.findProductById(id);

		return EntityModel.of(productDto,
				productLinks.linkToItemResource(productDto).withSelfRel());
	}

	@GetMapping
	public PagedModel<EntityModel<ProductDto>> products(final Pageable page,
			final PagedResourcesAssembler<ProductDto> assembler) {

		return assembler.toModel(productService.findAllProducts(page));
	}

}
