package com.enviro365.waste_management;

import com.enviro365.waste_management.model.RecyclingTip;
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
public class RecyclingTipControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllRecyclingTips() {
        ResponseEntity<List> response = restTemplate.getForEntity("/api/recycling-tips", List.class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().size() > 0);
    }

    @Test
    public void testGetRecyclingTipById() {
        ResponseEntity<RecyclingTip> response = restTemplate.getForEntity("/api/recycling-tips/1", RecyclingTip.class);
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getTip());
    }

    @Test
    public void testAddRecyclingTip() {
        RecyclingTip newTip = new RecyclingTip();
        newTip.setTip(Long.valueOf("New Tip"));
        ResponseEntity<RecyclingTip> response = restTemplate.postForEntity("/api/recycling-tips", newTip, RecyclingTip.class);
        assertNotNull(response.getBody());
        assertEquals("New Tip", response.getBody().getTip());
    }

    @Test
    public void testUpdateRecyclingTip() {
        RecyclingTip tip = restTemplate.getForEntity("/api/recycling-tips/1", RecyclingTip.class).getBody();
        tip.setTip(Long.valueOf("Updated Tip"));
        HttpEntity<RecyclingTip> requestUpdate = new HttpEntity<>(tip);
        ResponseEntity<RecyclingTip> response = restTemplate.exchange("/api/recycling-tips/1", HttpMethod.PUT, requestUpdate, RecyclingTip.class);
        assertNotNull(response.getBody());
        assertEquals("Updated Tip", response.getBody().getTip());
    }

    @Test
    public void testDeleteRecyclingTip() {
        restTemplate.delete("/api/recycling-tips/1");
        ResponseEntity<RecyclingTip> response = restTemplate.getForEntity("/api/recycling-tips/1", RecyclingTip.class);
        assertNull(response.getBody());
    }
}
