package org.openapitools.services;

import com.speed_liv.menu.model.Plat;
import java.util.Optional;
import org.openapitools.model.entity.Dish;
import org.openapitools.model.entity.Order;
import org.openapitools.model.entity.OrderConfirmation;
import org.openapitools.model.entity.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class CommandeService {

    private static final double PRICE_TOLERANCE = 0.01;

    private final RestaurantService restaurantService;
    private final MenuService menuService;

    public CommandeService(RestaurantService restaurantService, MenuService menuService) {
        this.restaurantService = restaurantService;
        this.menuService = menuService;
    }

    public OrderConfirmation validate(Order order) {
        if (order == null) {
            return new OrderConfirmation(false, 0.0, null, "Commande absente.");
        }
        if (order.getQuantity() <= 0) {
            return new OrderConfirmation(false, 0.0, null, "La quantite doit etre positive.");
        }
        if (order.getRestaurantId() == null) {
            return new OrderConfirmation(false, 0.0, null, "Identifiant restaurant manquant.");
        }
        if (order.getPlatId() == null) {
            return new OrderConfirmation(false, 0.0, null, "Identifiant plat manquant.");
        }
        if (order.getExpectedUnitPrice() <= 0.0) {
            return new OrderConfirmation(false, 0.0, null, "Prix attendu doit etre positif.");
        }

        Optional<Restaurant> restaurantOpt = restaurantService.getRestaurantById(order.getRestaurantId());
        if (!restaurantOpt.isPresent()) {
            return new OrderConfirmation(false, 0.0, null, "Restaurant introuvable.");
        }

        Restaurant restaurant = restaurantOpt.get();
        Dish dish = restaurant.findDishById(order.getPlatId());
        if (dish == null) {
            return new OrderConfirmation(false, 0.0, null, "Plat introuvable pour ce restaurant.");
        }
        if (!dish.isAvailable()) {
            return new OrderConfirmation(false, 0.0, null, "Plat indisponible.");
        }

        if (Math.abs(dish.getPrice() - order.getExpectedUnitPrice()) > PRICE_TOLERANCE) {
            return new OrderConfirmation(false, 0.0, null, "Le prix ne correspond pas au menu.");
        }

        Plat plat = menuService.getPlatById(Long.toString(dish.getId()))
            .orElseGet(() -> {
                Plat fallback = new Plat();
                fallback.setId(Long.toString(dish.getId()));
                fallback.setNom(dish.getName());
                fallback.setPrix((float) dish.getPrice());
                fallback.setDisponible(dish.isAvailable());
                return fallback;
            });

        double total = dish.getPrice() * order.getQuantity();
        return new OrderConfirmation(true, total, plat, "Commande validee.");
    }
}
