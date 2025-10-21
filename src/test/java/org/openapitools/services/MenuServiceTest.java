package org.openapitools.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.speed_liv.menu.model.Plat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    void getAllPlatsReturnsDataFromRepository() {
        List<Plat> plats = menuService.getAllPlats();
        assertEquals(4, plats.size(), "Expected four plats loaded from restaurants.json");
    }

    @Test
    void getPlatByIdReturnsMatchingPlat() {
        String platId = "1";
        assertTrue(menuService.getPlatById(platId).isPresent(), "Plat id 1 should exist");
    }
}
