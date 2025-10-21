package com.speed_liv.menu.services;

import java.util.List;
import java.util.Optional;
import com.speed_liv.menu.model.entity.Dish;
import com.speed_liv.menu.model.entity.Restaurant;
import com.speed_liv.menu.model.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(long restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }

    public Optional<Dish> getDishById(long dishId) {
        return restaurantRepository.findDishById(dishId);
    }
}
