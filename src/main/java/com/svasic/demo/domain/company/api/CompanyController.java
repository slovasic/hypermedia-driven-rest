package com.svasic.demo.domain.company.api;

import static com.svasic.demo.config.ApplicationUrls.REST_API_COMPANIES_V1;

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

	public CompanyController(CompanyService companyService,
			CompanyRepresentationModelProcessor companyRepresentationModelProcessor) {

		this.companyService = companyService;
		this.companyRepresentationModelProcessor = companyRepresentationModelProcessor;

	}

	@GetMapping(path = "{id}")
	public EntityModel<CompanyDto> company(@PathVariable("id") long id) {

		CompanyDto companyDto = companyService.findCompanyById(id);
		CompanyDto processedCompany = companyRepresentationModelProcessor
				.process(companyDto);
		return EntityModel.of(processedCompany);
	}

	@GetMapping
	public PagedModel<EntityModel<CompanyDto>> companies(
			final CompanyPagedResourceModelAssembler assembler) {

		return assembler.toModel(companyService.findAllCompanies());
	}

}
