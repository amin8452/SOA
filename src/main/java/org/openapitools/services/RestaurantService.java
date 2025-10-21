package org.openapitools.services;

import java.util.List;
import java.util.Optional;
import org.openapitools.model.entity.Dish;
import org.openapitools.model.entity.Restaurant;
import org.openapitools.model.repository.RestaurantRepository;
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
