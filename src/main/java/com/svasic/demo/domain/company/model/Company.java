package com.svasic.demo.domain.company.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.svasic.demo.domain.product.model.Product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "companies")
@Data
public class Company {

	@Id
	private long id;

	private String name;

	@OneToMany(mappedBy = "company")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Product> products;
}