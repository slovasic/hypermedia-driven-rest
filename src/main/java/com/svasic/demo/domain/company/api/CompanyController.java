package com.svasic.demo.domain.company.api;

import static com.svasic.demo.config.ApplicationUrls.REST_API_COMPANY;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svasic.demo.domain.company.services.CompanyService;
import com.svasic.demo.domain.company.view.CompanyDto;
import com.svasic.demo.domain.product.services.ProductService;
import com.svasic.demo.domain.product.view.ProductDto;
import com.svasic.demo.infra.assemblers.CompanyModelAssembler;

@RestController
@ExposesResourceFor(CompanyDto.class)
@RequestMapping(REST_API_COMPANY)
public class CompanyController {

	private final CompanyService companyService;

	private final ProductService productService;

	private final CompanyModelAssembler companyModelAssembler;

	public CompanyController(CompanyService companyService, ProductService productService,
			CompanyModelAssembler companyModelAssembler) {

		this.companyService = companyService;
		this.productService = productService;
		this.companyModelAssembler = companyModelAssembler;

	}

	@GetMapping(path = "{id}")
	public EntityModel<CompanyDto> company(@PathVariable("id") long id) {

		return companyModelAssembler.toModel(companyService.findCompanyById(id));
	}

	@GetMapping
	public CollectionModel<EntityModel<CompanyDto>> companies() {

		List<CompanyDto> companies = companyService.findAllCompanies();

		return companyModelAssembler.toCollectionOfCompaniesWithoutProducts(companies);
	}

	@GetMapping(path = "{id}/products")
	public CollectionModel<ProductDto> products(@PathVariable("id") long id) {

		return CollectionModel.of(productService.findAllByCompanyId(id));

	}

}
