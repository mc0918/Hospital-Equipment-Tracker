package com.trilogyed.hospitalequipmentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "equipment_location")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Equipment_Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String description;
    @NotNull
    private String location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Equipment_Location(){}

    public Equipment_Location(@NotNull String description, @NotNull String location) {
        this.description = description;
        this.location = location;
    }

    public Equipment_Location(Integer id, @NotNull String description, @NotNull String location) {
        this.id = id;
        this.description = description;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment_Location location1 = (Equipment_Location) o;
        return Objects.equals(id, location1.id) &&
                description.equals(location1.description) &&
                location.equals(location1.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, location);
    }

    @Override
    public String toString() {
        return "Equipment_Location{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
