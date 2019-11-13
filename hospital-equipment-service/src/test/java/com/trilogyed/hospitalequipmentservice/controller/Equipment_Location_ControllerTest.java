package com.trilogyed.hospitalequipmentservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.hospitalequipmentservice.dao.EquipmentRepository;
import com.trilogyed.hospitalequipmentservice.model.Equipment_Location;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Equipment_Location_Controller.class)
public class Equipment_Location_ControllerTest {
    @MockBean //autowire??
    private EquipmentRepository repository;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<Equipment_Location> jacksonTester;
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        JacksonTester.initFields(this, new ObjectMapper());
        mapper = new ObjectMapper();
    }

    @Test
    public void createLocation() throws Exception {
        Equipment_Location locationInput = new Equipment_Location(
                "Description",
                "Location"
        );
        Equipment_Location locationOutput = new Equipment_Location(
                1,
                "Description",
                "Location"
        );

        String inputJson = mapper.writeValueAsString(locationInput);
        String outputJson = mapper.writeValueAsString(locationOutput);

        given(repository.save(locationInput)).willReturn(locationOutput);
        mockMvc.perform(
                post("/equipment")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void getAllLocations() throws Exception {
        Equipment_Location locationOutput = new Equipment_Location(
                1,
                "Description",
                "Location"
        );
        List<Equipment_Location> listWeExpect = new ArrayList<>(Arrays.asList(locationOutput));
        String Json = mapper.writeValueAsString(listWeExpect);

        given(repository.findAll()).willReturn(listWeExpect);

        mockMvc.perform(
                get("/equipment")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(Json));
    }

    @Test
    public void getLocationsByDescription() throws Exception {
        Equipment_Location locationOutput = new Equipment_Location(
                1,
                "Description",
                "Location"
        );
        List<Equipment_Location> listWeExpect = new ArrayList<>(Arrays.asList(locationOutput));
        String Json = mapper.writeValueAsString(listWeExpect);

        given(repository.getByDescription("Description")).willReturn(listWeExpect);

        mockMvc.perform(
                get("/equipment/description/{description}", "Description")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(Json));
    }

    @Test
    public void getLocationById() throws Exception {
        Equipment_Location locationOutput = new Equipment_Location(
                1,
                "Description",
                "Location"
        );
        String outputJson = mapper.writeValueAsString(locationOutput);
        given(repository.getOne(1)).willReturn(locationOutput);

        mockMvc.perform(
                get("/equipment/{id}", 1)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void updateLocation() throws Exception {
        Equipment_Location location = new Equipment_Location(
                1,
                "Description",
                "Location"
        );
        String outputJson = mapper.writeValueAsString(location);

        mockMvc.perform(
                put("/equipment")
                        .content(outputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void deleteLocation() throws Exception {
        mockMvc.perform(
                delete("/equipment/{id}", 1)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}