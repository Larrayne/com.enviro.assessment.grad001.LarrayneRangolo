package com.enviro365.waste_management.repository;

import com.enviro365.waste_management.model.RecyclingTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecyclingTipRepository extends JpaRepository<RecyclingTip,Long>{
}
