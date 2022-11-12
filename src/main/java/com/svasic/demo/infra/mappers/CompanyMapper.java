package com.svasic.demo.infra.mappers;

import org.mapstruct.Mapper;

import com.svasic.demo.domain.company.model.Company;
import com.svasic.demo.domain.company.view.CompanyDto;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

	CompanyDto companyToDto(Company company);
}
