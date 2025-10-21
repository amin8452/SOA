package org.openapitools.controllers;

import javax.validation.Valid;
import org.openapitools.model.entity.Order;
import org.openapitools.model.entity.OrderConfirmation;
import org.openapitools.services.CommandeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${openapi.serviceMenu.base-path:/api/v1}")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @PostMapping("/commandes")
    public ResponseEntity<OrderConfirmation> validerCommande(@Valid @RequestBody Order order) {
        OrderConfirmation confirmation = commandeService.validate(order);
        if (confirmation.isAccepted()) {
            return ResponseEntity.ok(confirmation);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(confirmation);
    }
}
