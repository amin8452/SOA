package org.openapitools.model.repository;

import java.util.List;
import java.util.Optional;
import org.openapitools.model.entity.Dish;
import org.openapitools.model.entity.Restaurant;

public interface RestaurantRepository {

    List<Restaurant> findAll();

    Optional<Restaurant> findById(long id);

    Optional<Dish> findDishById(long dishId);
}
