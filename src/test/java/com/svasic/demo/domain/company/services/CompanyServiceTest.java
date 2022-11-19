package com.svasic.demo.domain.company.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.svasic.demo.domain.company.model.CompanyRepository;
import com.svasic.demo.infra.mappers.CompanyMapper;
import com.svasic.demo.services.CompanyService;

public class CompanyServiceTest {

	@InjectMocks
	private CompanyService companyService;

	@Mock
	private CompanyRepository companyRepository;

	@Mock
	private CompanyMapper companyMapper;

	@BeforeEach
	public void setUp() {

		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldThrowExceptionWhenCompanyDoesNotExist() {

		when(companyRepository.fetchCompanyById(anyLong())).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class,
				() -> companyService.findCompanyById(anyLong()));
	}

}
