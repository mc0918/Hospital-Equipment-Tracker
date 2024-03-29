package com.trilogyed.hospitalequipmentservice.dao;

import com.trilogyed.hospitalequipmentservice.model.Equipment_Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment_Location, Integer> {
    List<Equipment_Location> getByDescription(String description);
}
