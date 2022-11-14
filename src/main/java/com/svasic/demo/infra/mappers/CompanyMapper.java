package com.svasic.demo.infra.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.svasic.demo.domain.company.model.Company;
import com.svasic.demo.domain.company.view.CompanyDto;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

	CompanyDto companyToDto(Company company);

	/**
	 * Maps Company entity to CompanyDto, skipping products to avoid N+1 problem triggering
	 * 
	 * @param company
	 * @return companyDto
	 */
	@Mapping(target = "products", ignore = true)
	CompanyDto companyOnlyToDto(Company company);

}
