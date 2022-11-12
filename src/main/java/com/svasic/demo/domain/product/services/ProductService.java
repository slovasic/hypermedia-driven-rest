package com.svasic.demo.domain.product.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.svasic.demo.domain.product.model.Product;
import com.svasic.demo.domain.product.model.ProductRepository;
import com.svasic.demo.domain.product.view.ProductDto;
import com.svasic.demo.infra.mappers.ProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;

	public Set<ProductDto> findAllByCompanyId(long companyId) {
		Set<Product> productsByCompanyId = productRepository.findByCompanyId(companyId);
		Set<ProductDto> dtos = new HashSet<>();
		for (Product product : productsByCompanyId) {
			ProductDto productDto = productMapper.productToDto(product);
			dtos.add(productDto);
		}
		return dtos;
	}

	public ProductDto findProductById(long id) {
		Optional<Product> productOptional = productRepository.findById(id);
		if (productOptional.isPresent()) {
			return productMapper.productToDto(productOptional.get());
		}
		return null;
	}
}
