package com.svasic.demo.domain.company.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.svasic.demo.domain.company.model.Company;
import com.svasic.demo.domain.company.model.CompanyRepository;
import com.svasic.demo.domain.company.view.CompanyDto;
import com.svasic.demo.infra.mappers.CompanyMapper;
import com.svasic.demo.infra.mappers.CompanyOnlyMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CompanyService {

	private final CompanyRepository companyRepository;
	private final CompanyOnlyMapper companyOnlyMapper;
	private final CompanyMapper companyMapper;

	public List<CompanyDto> findAllCompanies() {

		Iterable<Company> companies = companyRepository.findAll();
		List<CompanyDto> companyDtos = new ArrayList<>();

		for (Company company : companies) {
			CompanyDto compDto = companyOnlyMapper.companyOnlyToDto(company);
			companyDtos.add(compDto);

		}

		return companyDtos;
	}

	public CompanyDto findCompanyById(long companyId) {
		Optional<Company> companyOptional = companyRepository.fetchCompanyById(companyId);
		if (companyOptional.isPresent()) {

			CompanyDto companyDto = companyMapper.companyToDto(companyOptional.get());

			return companyDto;
		}

		return null;
	}

}
