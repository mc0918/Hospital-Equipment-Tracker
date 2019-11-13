package com.trilogyed.hospitalequipmentservice.controller;

import com.trilogyed.hospitalequipmentservice.dao.EquipmentRepository;
import com.trilogyed.hospitalequipmentservice.model.Equipment_Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class Equipment_Location_Controller {

    @Autowired
    private EquipmentRepository repository;

    @PostMapping("/equipment")
    @ResponseStatus(HttpStatus.CREATED)
    public Equipment_Location createLocation(@RequestBody @Valid Equipment_Location el) {
        return repository.save(el);
    }

    @GetMapping("/equipment")
    @ResponseStatus(HttpStatus.OK)
    public List<Equipment_Location> getAllLocations() {
        return repository.findAll();
    }

    @GetMapping("/equipment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Equipment_Location getLocationById(@PathVariable int id) {
        return repository.getOne(id);
    }

    @PutMapping("/equipment")
    @ResponseStatus(HttpStatus.OK)
    public void updateLocation(@RequestBody @Valid Equipment_Location el){
        repository.save(el);
    }

    @DeleteMapping("/equipment/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable int id){
        repository.deleteById(id);
    }
}
