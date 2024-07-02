package com.enviro365.waste_management.controller;

import com.enviro365.waste_management.model.RecyclingTip;
import com.enviro365.waste_management.service.RecyclingTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recycling-tips")
public class RecyclingTipsController {

    @Autowired
    public RecyclingTipService service;

    @GetMapping
    public List<RecyclingTip>getReccyclingTips(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecyclingTip>getRecyclingTipById(@PathVariable Long id){
        Optional<RecyclingTip>tip = service.findById(id);
        return tip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public RecyclingTip createRecyclingTip(@Validated @RequestBody RecyclingTip tip){
        return service.save(tip);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RecyclingTip>updateRecyclingTip(@PathVariable Long id, @Validated @RequestBody RecyclingTip tip){
        if(!service.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        tip.setId(id);
        return ResponseEntity.ok(service.save(tip));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteRecyclingTip(@PathVariable Long id){
        if(!service.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
