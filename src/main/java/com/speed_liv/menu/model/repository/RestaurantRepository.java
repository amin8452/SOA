package com.speed_liv.menu.model.repository;

import java.util.List;
import java.util.Optional;
import com.speed_liv.menu.model.entity.Dish;
import com.speed_liv.menu.model.entity.Restaurant;

public interface RestaurantRepository {

    List<Restaurant> findAll();

    Optional<Restaurant> findById(long id);

    Optional<Dish> findDishById(long dishId);
}
