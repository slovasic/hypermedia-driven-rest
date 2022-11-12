package com.svasic.demo.domain.company.api;

import static com.svasic.demo.config.ApplicationUrls.REST_API_COMPANY;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class CompanyEndpointTest {

	@Autowired
	WebTestClient webTestClient;

	@Test
	void shouldReturnEmbeddedLinkForCompanies() throws Exception {

		webTestClient.get().uri(REST_API_COMPANY).exchange().expectBody()
				.jsonPath("$._links.self.href")
				.value(t -> t.equals("http://localhost/api/v1/companies"))
				.jsonPath("$._embedded.companyDtoList[0]._links.self.href")
				.value(t -> t.equals("http://localhost/api/v1/companies/1"))
				.jsonPath("$._embedded.companyDtoList[0].products").doesNotExist();

	}

	@Test
	void shouldReturnCompanyWithProducts() throws Exception {

		webTestClient.get().uri(REST_API_COMPANY + "/2").exchange().expectBody()
				.jsonPath("$.name").value(t -> t.equals("Company2"))
				.jsonPath("$.products[0].name").value(t -> t.equals("product4"))
				.jsonPath("$.products[1]._links.self.href")
				.value(t -> t.equals("http://localhost/api/v1/companies/2/products/3"));

	}

}
