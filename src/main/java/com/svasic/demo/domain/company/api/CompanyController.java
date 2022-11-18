package com.svasic.demo.domain.company.api;

import static com.svasic.demo.config.ApplicationUrls.REST_API_COMPANIES_V1;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svasic.demo.domain.company.services.CompanyService;
import com.svasic.demo.domain.company.view.CompanyDto;
import com.svasic.demo.infra.assemblers.CompanyPagedResourceModelAssembler;
import com.svasic.demo.infra.processors.CompanyRepresentationModelProcessor;

@RestController
@RequestMapping(REST_API_COMPANIES_V1)
public class CompanyController {

	private final CompanyService companyService;

	private final CompanyRepresentationModelProcessor companyRepresentationModelProcessor;
	private final CompanyPagedResourceModelAssembler assembler;

	public CompanyController(CompanyService companyService,
			CompanyRepresentationModelProcessor companyRepresentationModelProcessor,
			CompanyPagedResourceModelAssembler companyPagedResourceModelAssembler) {

		this.companyService = companyService;
		this.companyRepresentationModelProcessor = companyRepresentationModelProcessor;
		this.assembler = companyPagedResourceModelAssembler;
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
