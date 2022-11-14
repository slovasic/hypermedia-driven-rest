package com.svasic.demo.infra.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.data.domain.Page;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.util.UriComponents;

import com.svasic.demo.domain.company.api.CompanyController;
import com.svasic.demo.domain.company.view.CompanyDto;

/**
 * Converts Page to PageModel and adding self link to DTO object
 * 
 * @author Slobodan Vasic
 *
 */
public class CompanyPagedResourceModelAssembler
		extends PagedResourcesAssembler<CompanyDto> {

	public CompanyPagedResourceModelAssembler(
			HateoasPageableHandlerMethodArgumentResolver resolver,
			UriComponents baseUri) {

		super(resolver, baseUri);
	}

	@Override
	public PagedModel<EntityModel<CompanyDto>> toModel(Page<CompanyDto> page) {

		for (CompanyDto companyDto : page) {
			companyDto.add(linkTo(WebMvcLinkBuilder.methodOn(CompanyController.class)
					.company(companyDto.getId())).withSelfRel());
		}

		return super.toModel(page);
	}

}
