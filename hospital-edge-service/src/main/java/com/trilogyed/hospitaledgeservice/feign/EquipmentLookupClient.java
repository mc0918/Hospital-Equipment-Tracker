package com.trilogyed.hospitaledgeservice.feign;

import com.trilogyed.hospitaledgeservice.model.EquipmentEntry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "hospital-service")
public interface EquipmentLookupClient {
    @GetMapping("/equipment/description/{description}")
    public List<EquipmentEntry> getByDescription(@PathVariable String description);

    @PutMapping("/equipment")
    public String updateEquipment(@RequestBody @Valid EquipmentEntry equipmentEntry);
}
