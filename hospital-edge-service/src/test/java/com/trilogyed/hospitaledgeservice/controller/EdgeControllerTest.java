package com.trilogyed.hospitaledgeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.hospitaledgeservice.feign.EquipmentLookupClient;
import com.trilogyed.hospitaledgeservice.model.EquipmentEntry;
import com.trilogyed.hospitaledgeservice.service.ServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EdgeController.class)
public class EdgeControllerTest {
    //Todo: get tests to work and do service layer tests

    @MockBean
    private ServiceLayer serviceLayer;

    @MockBean
    private DiscoveryClient discoveryClient;

    @Autowired
    private EquipmentLookupClient equipmentLookupClient;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<EquipmentEntry> jacksonTester;
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        JacksonTester.initFields(this, new ObjectMapper());
        mapper = new ObjectMapper();
    }

    @Test
    public void shouldGetEquipmentByDescription() throws Exception{
        EquipmentEntry entryInput = new EquipmentEntry(1,"Description", "Location");
        List<EquipmentEntry> inputList = new ArrayList<>(Arrays.asList(entryInput));
        String inputJson = mapper.writeValueAsString(inputList);

        given(serviceLayer.getByDescription("Description")).willReturn(inputList);

        mockMvc.perform(
                get("/equipment/description/{description}", "Description")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(inputJson));
    }

    @Test
    public void shouldUpdateEquipmentLocationAndReturnMessage() throws Exception{
        EquipmentEntry entryInput = new EquipmentEntry(1,"Description", "Location");
        String inputJson = mapper.writeValueAsString(entryInput);

        given(serviceLayer.updateEquipment(entryInput)).willReturn("Success!");

        mockMvc.perform(
                put("/equipment")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Success!"));

    }
}