package com.speed_liv.menu.api;

import com.speed_liv.menu.model.Plat;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import com.speed_liv.menu.services.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2025-10-14T09:37:49.653006400+01:00[Africa/Casablanca]",
    comments = "Generator version: 7.7.0"
)
@Controller
@RequestMapping("${openapi.serviceMenu.base-path:/api/v1}")
public class MenusApiController implements MenusApi {

    private final NativeWebRequest request;
    private final MenuService menuService;

    public MenusApiController(MenuService menuService, NativeWebRequest request) {
        this.menuService = menuService;
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<List<Plat>> menusGet() {
        List<Plat> plats = menuService.getAllPlats();
        return ResponseEntity.ok(plats);
    }

    @Override
    public ResponseEntity<Plat> menusIdGet(@PathVariable("id") String id) {
        return menuService.getPlatById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
