package com.svasic.demo.domain.product.model;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Set<Product> findByCompanyId(long companyId);
}
