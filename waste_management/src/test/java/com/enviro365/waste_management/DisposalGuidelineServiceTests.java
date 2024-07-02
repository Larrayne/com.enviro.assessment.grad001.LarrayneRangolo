package com.enviro365.waste_management;

import com.enviro365.waste_management.model.DisposalGuideline;
import com.enviro365.waste_management.service.DisposalGuidelineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DisposalGuidelineServiceTests {

    @Autowired
    private DisposalGuidelineService disposalService;

    @Test
    public void testGetAllDisposalGuidelines() {
        List<DisposalGuideline> guidelines = disposalService.findAll();
        assertNotNull(guidelines);
        assertTrue(guidelines.size() > 0);
    }


    @Test
    public void testAddDisposalGuideline() {
        DisposalGuideline newGuideline = new DisposalGuideline();
        newGuideline.setName("New Guideline");
        DisposalGuideline savedGuideline = disposalService.save(newGuideline);
        assertNotNull(savedGuideline);
        assertEquals("New Guideline", savedGuideline.getName());
    }


    @Test
    public void testDeleteDisposalGuideline() {
        disposalService.deleteById(1L);
        Optional<DisposalGuideline> guideline = disposalService.findById(1L);
        assertNull(guideline);
    }
}
