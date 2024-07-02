package com.enviro365.waste_management;

import com.enviro365.waste_management.model.RecyclingTip;
import com.enviro365.waste_management.repository.RecyclingTipRepository;
import com.enviro365.waste_management.service.RecyclingTipService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class RecyclingTipServiceTests {

    @Mock
    private RecyclingTipRepository recyclingTipRepository;

    @InjectMocks
    private RecyclingTipService recyclingTipService;

    @Test
    public void testGetAllRecyclingTips() {
        RecyclingTip tip1 = new RecyclingTip(1L, "Tip 1");
        RecyclingTip tip2 = new RecyclingTip(2L, "Tip 2");
        List<RecyclingTip> tips = Arrays.asList(tip1, tip2);

        // Mock the behavior of the repository
        given(recyclingTipRepository.findAll()).willReturn(tips);

        // Call the service method
        List<RecyclingTip> result = recyclingTipService.findAll();

        // Assert the results
        assertThat(result).isEqualTo(tips);
    }
}
