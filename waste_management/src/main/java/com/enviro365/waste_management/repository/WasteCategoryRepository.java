package com.enviro365.waste_management.repository;

import com.enviro365.waste_management.model.WasteCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Long>{

}
