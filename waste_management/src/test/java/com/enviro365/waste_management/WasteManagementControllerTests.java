package com.enviro365.waste_management;

import com.enviro365.waste_management.model.WasteCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WasteManagementControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllWasteCategories() {
        ResponseEntity<List> response = restTemplate.getForEntity("/api/waste-categories", List.class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() > 0);
    }

    @Test
    public void testGetWasteCategoryById() {
        ResponseEntity<WasteCategory> response = restTemplate.getForEntity("/api/waste-categories/1", WasteCategory.class);
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    public void testAddWasteCategory() {
        WasteCategory newCategory = new WasteCategory();
        newCategory.setName("New Category");
        ResponseEntity<WasteCategory> response = restTemplate.postForEntity("/api/waste-categories", newCategory, WasteCategory.class);
        assertNotNull(response.getBody());
        assertEquals("New Category", response.getBody().getName());
    }

    @Test
    public void testUpdateWasteCategory() {
        WasteCategory category = restTemplate.getForEntity("/api/waste-categories/1", WasteCategory.class).getBody();
        category.setName("Updated Category");
        HttpEntity<WasteCategory> requestUpdate = new HttpEntity<>(category);
        ResponseEntity<WasteCategory> response = restTemplate.exchange("/api/waste-categories/1", HttpMethod.PUT, requestUpdate, WasteCategory.class);
        assertNotNull(response.getBody());
        assertEquals("Updated Category", response.getBody().getName());
    }

    @Test
    public void testDeleteWasteCategory() {
        restTemplate.delete("/api/waste-categories/1");
        ResponseEntity<WasteCategory> response = restTemplate.getForEntity("/api/waste-categories/1", WasteCategory.class);
        assertNull(response.getBody());
    }
}
