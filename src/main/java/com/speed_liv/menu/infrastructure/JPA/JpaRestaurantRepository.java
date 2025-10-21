package com.speed_liv.menu.infrastructure.JPA;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.speed_liv.menu.model.entity.Dish;
import com.speed_liv.menu.model.entity.Restaurant;
import com.speed_liv.menu.model.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class JpaRestaurantRepository implements RestaurantRepository {

    private final List<Restaurant> restaurants;
    private final Map<Long, Dish> dishesIndex;

    public JpaRestaurantRepository(
        ObjectMapper objectMapper,
        @Value("classpath:restaurants.json") Resource restaurantsResource
    ) {
        this.restaurants = readRestaurants(objectMapper, restaurantsResource);
        this.dishesIndex = indexDishes(restaurants);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurants;
    }

    @Override
    public Optional<Restaurant> findById(long id) {
        return restaurants.stream()
            .filter(resto -> resto.getId() == id)
            .findFirst();
    }

    @Override
    public Optional<Dish> findDishById(long dishId) {
        return Optional.ofNullable(dishesIndex.get(dishId));
    }

    private List<Restaurant> readRestaurants(ObjectMapper objectMapper, Resource restaurantsResource) {
        try (InputStream inputStream = restaurantsResource.getInputStream()) {
            List<Restaurant> data = objectMapper.readValue(
                inputStream,
                new TypeReference<List<Restaurant>>() { }
            );
            return Collections.unmodifiableList(data);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load restaurants.json", e);
        }
    }

    private Map<Long, Dish> indexDishes(List<Restaurant> restaurants) {
        return restaurants.stream()
            .flatMap(restaurant -> restaurant.getPlats().stream())
            .collect(Collectors.toMap(Dish::getId, Function.identity(), (first, duplicate) -> first));
    }
}
