package org.openapitools.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Restaurant {

    private long id;
    private String name;
    private List<Dish> plats = new ArrayList<>();

    public Restaurant() {
        // Jackson deserialization
    }

    public Restaurant(long id, String name, List<Dish> plats) {
        this.id = id;
        this.name = name;
        if (plats != null) {
            this.plats = new ArrayList<>(plats);
        }
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("plats")
    public List<Dish> getPlats() {
        return Collections.unmodifiableList(plats);
    }

    public void setPlats(List<Dish> plats) {
        this.plats = plats != null ? new ArrayList<>(plats) : new ArrayList<>();
    }

    public Dish findDishById(long dishId) {
        return plats.stream()
            .filter(d -> d.getId() == dishId)
            .findFirst()
            .orElse(null);
    }
}
