package com.svasic.demo.domain.product.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

		Optional<Product> productOptional = productRepository.findById(id);

		if (productOptional.isPresent()) {
			return productMapper.productToDto(productOptional.get());
		}

		return null;
	}

	public Page<ProductDto> findAllProducts(Pageable pageable) {

		List<Product> products = productRepository.findAll();
		List<ProductDto> productDtos = new ArrayList<>();

		for (Product product : products) {
			productDtos.add(productMapper.productToDto(product));
		}

		return new PageImpl<>(productDtos, pageable, 5);
	}
}
