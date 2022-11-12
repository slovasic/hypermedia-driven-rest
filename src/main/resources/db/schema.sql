DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS products;

CREATE TABLE IF NOT EXISTS companies 
(
	id INT AUTO_INCREMENT,
	name varchar(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS products
(
	id BIGINT AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	company_id INT,
	
	CONSTRAINT products_companies_fk
		FOREIGN KEY (company_id)
			REFERENCES companies(id)
	
);