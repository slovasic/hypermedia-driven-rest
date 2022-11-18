package com.svasic.demo.domain.product.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.svasic.demo.domain.company.model.Company;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "products")
@Data
public class Product {

	@Id
	private long id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Company company;
}
