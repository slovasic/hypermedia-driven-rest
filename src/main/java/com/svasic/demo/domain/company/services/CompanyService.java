package com.svasic.demo.domain.company.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.svasic.demo.domain.company.model.Company;
import com.svasic.demo.domain.company.model.CompanyRepository;
import com.svasic.demo.domain.company.view.CompanyDto;
import com.svasic.demo.infra.mappers.CompanyMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CompanyService {

	private final CompanyRepository companyRepository;
	private final CompanyMapper companyMapper;

	public Page<CompanyDto> findAllCompanies() {

		Iterable<Company> companies = companyRepository.findAll();
		List<CompanyDto> companyDtos = new ArrayList<>();

		for (Company company : companies) {
			CompanyDto compDto = companyMapper.companyOnlyToDto(company);
			companyDtos.add(compDto);

		}

		return new PageImpl<>(companyDtos);
	}

	public CompanyDto findCompanyById(long companyId) {

		Optional<Company> companyOptional = companyRepository.fetchCompanyById(companyId);

		CompanyDto companyDto = companyMapper.companyToDto(companyOptional.get());

		return companyDto;

	}

}
