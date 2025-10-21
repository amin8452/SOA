package com.speed_liv.menu.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.speed_liv.menu.model.entity.Order;
import com.speed_liv.menu.model.entity.OrderConfirmation;
import org.junit.jupiter.api.Test;
import org.openapitools.OpenApiGeneratorApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = OpenApiGeneratorApplication.class)
class CommandeServiceTest {

    @Autowired
    private CommandeService commandeService;

    @Test
    void validateAcceptsOrderWithValidPrice() {
        Order order = new Order(1L, 1L, 2, 8.5);
        OrderConfirmation confirmation = commandeService.validate(order);
        assertTrue(confirmation.isAccepted(), "Order should be accepted when price matches and plat available.");
    }

    @Test
    void validateRejectsOrderWhenPriceMismatch() {
        Order order = new Order(1L, 1L, 1, 9.9);
        OrderConfirmation confirmation = commandeService.validate(order);
        assertFalse(confirmation.isAccepted(), "Order should be rejected when expected price mismatches.");
    }
}
