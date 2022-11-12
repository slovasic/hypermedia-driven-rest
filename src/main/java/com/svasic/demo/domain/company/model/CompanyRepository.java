package com.svasic.demo.domain.company.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {

	@Query(value = "SELECT c FROM Company c JOIN FETCH c.products p where c.id = :id")
	Optional<Company> fetchCompanyById(@Param("id") long id);

}