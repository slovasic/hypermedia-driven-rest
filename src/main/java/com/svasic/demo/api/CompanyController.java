package com.svasic.demo.api;

import static com.svasic.demo.config.ApplicationUrls.REST_API_COMPANIES_V1;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svasic.demo.domain.company.view.CompanyDto;
import com.svasic.demo.infra.assemblers.CompanyPagedRepresentationModelAssembler;
import com.svasic.demo.infra.processors.CompanyRepresentationModelProcessor;
import com.svasic.demo.services.CompanyService;

@RestController
@RequestMapping(REST_API_COMPANIES_V1)
public class CompanyController {

	private final CompanyService companyService;

	private final CompanyRepresentationModelProcessor companyRepresentationModelProcessor;
	private final CompanyPagedRepresentationModelAssembler assembler;

	public CompanyController(CompanyService companyService,
			CompanyRepresentationModelProcessor companyRepresentationModelProcessor,
			CompanyPagedRepresentationModelAssembler companyPagedRepresentationModelAssembler) {

		this.companyService = companyService;
		this.companyRepresentationModelProcessor = companyRepresentationModelProcessor;
		this.assembler = companyPagedRepresentationModelAssembler;
	}

	@GetMapping(path = "{id}")
	public EntityModel<CompanyDto> company(@PathVariable("id") long id) {

		CompanyDto companyDto = companyService.findCompanyById(id);
		return EntityModel.of(companyRepresentationModelProcessor.process(companyDto));
	}

	@GetMapping
	public PagedModel<EntityModel<CompanyDto>> companies(final Pageable page) {

		return assembler.toModel(companyService.findAllCompanies(page));
	}

}
