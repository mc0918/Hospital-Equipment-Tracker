package com.trilogyed.hospitaledgeservice.model;


import javax.validation.constraints.NotNull;
import java.util.Objects;

public class EquipmentEntry {
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

    public EquipmentEntry(){}

    public EquipmentEntry(@NotNull String description, @NotNull String location) {
        this.description = description;
        this.location = location;
    }

    public EquipmentEntry(Integer id, @NotNull String description, @NotNull String location) {
        this.id = id;
        this.description = description;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentEntry that = (EquipmentEntry) o;
        return Objects.equals(id, that.id) &&
                description.equals(that.description) &&
                location.equals(that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, location);
    }

    @Override
    public String toString() {
        return "EquipmentEntry{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
