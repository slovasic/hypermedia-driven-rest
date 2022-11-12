package com.svasic.demo.infra.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.TypedEntityLinks.ExtendedTypedEntityLinks;
import org.springframework.stereotype.Component;

import com.svasic.demo.domain.company.api.CompanyController;
import com.svasic.demo.domain.company.view.CompanyDto;

@Component
public class CompanyModelAssembler
		implements RepresentationModelAssembler<CompanyDto, EntityModel<CompanyDto>> {

	private final ExtendedTypedEntityLinks<CompanyDto> companyLinks;

	public CompanyModelAssembler(EntityLinks entityLinks) {
		this.companyLinks = entityLinks.forType(CompanyDto.class, CompanyDto::getId);
	}

	@Override
	public EntityModel<CompanyDto> toModel(CompanyDto company) {

		company.getProducts().stream().forEach(product -> product
				.add(linkTo(methodOn(CompanyController.class).products(company.getId()))
						.slash(product.getId()).withSelfRel()));
		return EntityModel.of(company,
				companyLinks.linkToItemResource(company).withSelfRel());
	}

	public CollectionModel<EntityModel<CompanyDto>> toCollection(
			Iterable<CompanyDto> companies) {
		return toCollectionModel(companies);
	}

	public CollectionModel<EntityModel<CompanyDto>> toCollectionOfCompaniesWithoutProducts(
			Iterable<CompanyDto> companies) {
		List<EntityModel<CompanyDto>> listEntityModels = new ArrayList<>();
		for (CompanyDto companyDto : companies) {
			listEntityModels.add(EntityModel.of(companyDto,
					companyLinks.linkForItemResource(companyDto).withSelfRel()));
		}
		return CollectionModel.of(listEntityModels,
				companyLinks.linkToCollectionResource());
	}

}
