package com.svasic.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

	public ProductDto findProductById(long id) {

		return productMapper.productToDto(productRepository.findById(id).get());

	}

	public Page<ProductDto> findAllProducts(Pageable page) {

		List<ProductDto> productDtos = new ArrayList<>();

		productRepository.findAll(page).stream()
				.forEach(product -> productDtos.add(productMapper.productToDto(product)));

		return new PageImpl<>(productDtos, page, productRepository.count());
	}
}
