package com.trilogyed.hospitaledgeservice.controller;

import com.sun.javafx.geom.Edge;
import com.trilogyed.hospitaledgeservice.feign.EquipmentLookupClient;
import com.trilogyed.hospitaledgeservice.model.EquipmentEntry;
import com.trilogyed.hospitaledgeservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
//@RefreshScope
public class EdgeController {

    @Autowired
    private ServiceLayer serviceLayer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private EquipmentLookupClient equipmentLookupClient;

    public EdgeController(){}

    public EdgeController(EquipmentLookupClient equipmentLookupClient) {
        this.equipmentLookupClient = equipmentLookupClient;
    }

    @PutMapping("/equipment")
    @ResponseStatus(HttpStatus.OK)
    public String updateEquipment(@RequestBody @Valid EquipmentEntry equipmentEntry){
        return serviceLayer.updateEquipment(equipmentEntry);

//        equipmentLookupClient.updateEquipment(equipmentEntry)
    }

    //Todo: Mapping for getting equipment by description
}
