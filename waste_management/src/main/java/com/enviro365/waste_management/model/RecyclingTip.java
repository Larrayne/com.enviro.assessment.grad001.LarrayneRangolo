package com.enviro365.waste_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.NonNull;

@Entity
@Table
public class RecyclingTip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String tip;

    public RecyclingTip() {}

    public RecyclingTip(Long id, String tip) {
        this.id = id;
        this.tip = tip;
    }

    public Long getTip(){
        return id;
    }

    public String getName(){
        return tip;
    }

    public void setTip(Long id) {
        this.id = id;
    }

    public void setName(String name){
        this.tip = tip;
    }



}
