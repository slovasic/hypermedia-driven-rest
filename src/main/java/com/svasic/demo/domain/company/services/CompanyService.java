package com.svasic.demo.domain.company.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.svasic.demo.domain.company.model.CompanyRepository;
import com.svasic.demo.domain.company.view.CompanyDto;
import com.svasic.demo.infra.mappers.CompanyMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CompanyService {

	private final CompanyRepository companyRepository;
	private final CompanyMapper companyMapper;

	public Page<CompanyDto> findAllCompanies(Pageable page) {

		List<CompanyDto> companyDtos = new ArrayList<>();

		companyRepository.findAll(page).stream().forEach(
				company -> companyDtos.add(companyMapper.companyOnlyToDto(company)));

		return new PageImpl<>(companyDtos, page, companyRepository.count());
	}

	public CompanyDto findCompanyById(long companyId) {

		return companyMapper
				.companyToDto(companyRepository.fetchCompanyById(companyId).get());

	}

}
