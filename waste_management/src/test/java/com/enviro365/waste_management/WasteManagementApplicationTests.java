package com.enviro365.waste_management;

import com.enviro365.waste_management.model.WasteCategory;
import com.enviro365.waste_management.repository.WasteCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WasteManagementApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private WasteCategoryRepository wasteCategoryRepository;

	@BeforeEach
	public void setup() {
		wasteCategoryRepository.deleteAll();
		WasteCategory category1 = new WasteCategory();
		WasteCategory category2 = new WasteCategory();
		wasteCategoryRepository.saveAll(List.of(category1, category2));
	}

	@Test
	public void testGetWasteCategories() {
		ResponseEntity<WasteCategory[]> response = restTemplate.getForEntity("/api/waste-categories", WasteCategory[].class);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).hasSize(2);
		assertThat(response.getBody()[0].getName()).isEqualTo("Plastic");
		assertThat(response.getBody()[1].getName()).isEqualTo("Metal");
	}

	@Test
	public void testAddWasteCategory() {
		WasteCategory category = new WasteCategory();

		ResponseEntity<WasteCategory> response = restTemplate.postForEntity("/api/waste-categories", category, WasteCategory.class);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getName()).isEqualTo("Glass");
		assertThat(response.getBody().getDescription()).isEqualTo("Recyclable glass");

		List<WasteCategory> categories = wasteCategoryRepository.findAll();
		assertThat(categories).hasSize(3);
		assertThat(categories).extracting(WasteCategory::getName).contains("Glass");
	}
}
