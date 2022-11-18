package com.svasic.demo.infra.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

import com.svasic.demo.domain.company.api.CompanyController;
import com.svasic.demo.domain.company.view.CompanyDto;

/**
 * Converts Page to PageModel and adding self link to DTO object
 * 
 * @author Slobodan Vasic
 *
 */
@Component
@Lazy(false)
public class CompanyPagedResourceModelAssembler
		extends PagedResourcesAssembler<CompanyDto> {

	public CompanyPagedResourceModelAssembler(
			HateoasPageableHandlerMethodArgumentResolver resolver,
			@Nullable UriComponents baseUri) {

		super(resolver, baseUri);
	}

	@Override
	public PagedModel<EntityModel<CompanyDto>> toModel(Page<CompanyDto> page) {

		page.stream().forEach(companyDto -> companyDto
				.add(linkTo(methodOn(CompanyController.class).company(companyDto.getId()))
						.withSelfRel()));

		setForceFirstAndLastRels(true);

		return super.toModel(page);
	}

}
