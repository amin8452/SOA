package org.openapitools.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openapitools.model.entity.Order;
import org.openapitools.model.entity.OrderConfirmation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
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
