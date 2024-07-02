package com.enviro365.waste_management;

import com.enviro365.waste_management.model.WasteCategory;
import com.enviro365.waste_management.repository.WasteCategoryRepository;
import com.enviro365.waste_management.service.WasteCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class WasteCategoryServiceTest {

    @InjectMocks
    private WasteCategoryService wasteCategoryService;

    @Mock
    private WasteCategoryRepository wasteCategoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategories() {
        WasteCategory category1 = new WasteCategory();
        category1.setName("Plastic");
        category1.setDescription("Items made of plastic");

        WasteCategory category2 = new WasteCategory();
        category2.setName("Glass");
        category2.setDescription("Items made of glass");

        when(wasteCategoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        List<WasteCategory> categories = wasteCategoryService.getAllWasteCategories();
        assertEquals(2, categories.size());
        verify(wasteCategoryRepository, times(1)).findAll();
    }

    @Test
    void testAddCategory() {
        WasteCategory category = new WasteCategory();
        category.setName("Plastic");
        category.setDescription("Items made of plastic");

        when(wasteCategoryRepository.save(any(WasteCategory.class))).thenReturn(category);

        WasteCategory savedCategory = wasteCategoryService.save(category);
        assertEquals("Plastic", savedCategory.getName());
        verify(wasteCategoryRepository, times(1)).save(category);
    }
}