package com.enviro365.waste_management.controller;

import com.enviro365.waste_management.model.DisposalGuideline;
import com.enviro365.waste_management.service.DisposalGuidelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/disposal-guidelines")
public class DisposalGuidelineController {

    @Autowired
    private DisposalGuidelineService service;

    @GetMapping
    public List<DisposalGuideline>getAllDisposalGuidelines(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisposalGuideline>getDisposalGuidelineById(@PathVariable Long id){
        Optional<DisposalGuideline> guideline = service.findById(id);
        return guideline.map(ResponseEntity::ok).orElseGet(() ->ResponseEntity.notFound().build());
    }

    @PostMapping
    public DisposalGuideline createDisposalGuideline(@Validated @RequestBody DisposalGuideline guideline){
        return service.save(guideline);
    }

    @PutMapping
    public ResponseEntity<DisposalGuideline> updateDisposalGuideline(@PathVariable Long id, @Validated @RequestBody DisposalGuideline guideline){
        if(!service.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        guideline.setId(id);
        return ResponseEntity.ok(service.save(guideline));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteDisposalGuideline(@PathVariable Long id){
        if(!service.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

