package com.enviro365.waste_management;

import com.enviro365.waste_management.controller.DisposalGuidelineController;
import com.enviro365.waste_management.model.DisposalGuideline;
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
public class DisposalGuidelineControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllDisposalGuidelines() {
        ResponseEntity<List> response = restTemplate.getForEntity("/api/disposal-guidelines", List.class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() > 0);
    }

    @Test
    public void testGetDisposalGuidelineById() {
        ResponseEntity<DisposalGuideline> response = restTemplate.getForEntity("/api/disposal-guidelines/1", DisposalGuideline.class);
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    public void testAddDisposalGuideline() {
        DisposalGuideline newGuideline = new DisposalGuideline();
        newGuideline.setName("New Guideline");
        ResponseEntity<DisposalGuideline> response = restTemplate.postForEntity("/api/disposal-guidelines", newGuideline, DisposalGuideline.class);
        assertNotNull(response.getBody());
        assertEquals("New Guideline", response.getBody().getName());
    }

    @Test
    public void testUpdateDisposalGuideline() {
        DisposalGuideline guideline = restTemplate.getForEntity("/api/disposal-guidelines/1", DisposalGuideline.class).getBody();
        guideline.setName("Updated Guideline");
        HttpEntity<DisposalGuideline> requestUpdate = new HttpEntity<>(guideline);
        ResponseEntity<DisposalGuideline> response = restTemplate.exchange("/api/disposal-guidelines/1", HttpMethod.PUT, requestUpdate, DisposalGuideline.class);
        assertNotNull(response.getBody());
        assertEquals("Updated Guideline", response.getBody().getName());
    }

    @Test
    public void testDeleteDisposalGuideline() {
        restTemplate.delete("/api/disposal-guidelines/1");
        ResponseEntity<DisposalGuideline> response = restTemplate.getForEntity("/api/disposal-guidelines/1", DisposalGuideline.class);
        assertNull(response.getBody());


    }
}
