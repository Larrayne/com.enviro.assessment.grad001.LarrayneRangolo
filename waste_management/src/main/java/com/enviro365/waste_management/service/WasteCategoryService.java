package com.enviro365.waste_management.service;


import com.enviro365.waste_management.model.WasteCategory;
import com.enviro365.waste_management.repository.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WasteCategoryService {

    @Autowired
    private WasteCategoryRepository repository;

    public List<WasteCategory> findAll(){
        return repository.findAll();
    }

    public Optional<WasteCategory> findById(Long id){
        return repository.findById(id);
    }

    public WasteCategory save(WasteCategory category){
        return repository.save(category);
    }


    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
