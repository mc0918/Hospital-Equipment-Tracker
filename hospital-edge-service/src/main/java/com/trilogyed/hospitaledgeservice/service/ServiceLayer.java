package com.trilogyed.hospitaledgeservice.service;

import com.trilogyed.hospitaledgeservice.model.EquipmentEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceLayer {
    @Autowired
    public ServiceLayer() {}

    public List<EquipmentEntry> getByDescription(String description){
        return null;
        //Todo: use feign client to get all equipment with specified description
    }

    public String updateEquipment(EquipmentEntry equipmentEntry) {
        return "Success!";
        //Todo: return either success or error message
    }
}
