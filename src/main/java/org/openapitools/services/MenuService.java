package org.openapitools.services;

import com.speed_liv.menu.model.Plat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.openapitools.model.entity.Dish;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private final RestaurantService restaurantService;

    public MenuService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public List<Plat> getAllPlats() {
        return restaurantService.getAllRestaurants().stream()
            .flatMap(restaurant -> restaurant.getPlats().stream())
            .map(this::toPlat)
            .collect(Collectors.toList());
    }

    public Optional<Plat> getPlatById(String platId) {
        return parseId(platId)
            .flatMap(restaurantService::getDishById)
            .map(this::toPlat);
    }

    public Optional<Dish> getDishById(String platId) {
        return parseId(platId)
            .flatMap(restaurantService::getDishById);
    }

    private Plat toPlat(Dish dish) {
        Plat plat = new Plat();
        plat.setId(Long.toString(dish.getId()));
        plat.setNom(dish.getName());
        plat.setPrix((float) dish.getPrice());
        plat.setDisponible(dish.isAvailable());
        return plat;
    }

    private Optional<Long> parseId(String platId) {
        if (platId == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(Long.parseLong(platId));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }
}
