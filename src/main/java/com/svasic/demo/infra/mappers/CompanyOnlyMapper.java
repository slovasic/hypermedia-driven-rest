package com.svasic.demo.infra.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.svasic.demo.domain.company.model.Company;
import com.svasic.demo.domain.company.view.CompanyDto;

@Mapper(componentModel = "spring")
public interface CompanyOnlyMapper {
	
	@Mapping(target = "products", ignore = true)
	CompanyDto companyOnlyToDto(Company company);
}
