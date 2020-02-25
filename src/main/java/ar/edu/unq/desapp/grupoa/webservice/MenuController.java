package ar.edu.unq.desapp.grupoa.webservice;

import ar.edu.unq.desapp.grupoa.model.Menu;
import ar.edu.unq.desapp.grupoa.model.enums.Category;
import ar.edu.unq.desapp.grupoa.service.MenuService;
import ar.edu.unq.desapp.grupoa.service.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@CrossOrigin
@RequestMapping("/rest/menu")
public class MenuController {

    private MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<String> createMenu(final @RequestBody MenuDTO menuDTO) {
        ResponseEntity<String> response;
        try {
            menuService.createMenu(menuDTO);
            response = new ResponseEntity<>("Menu created successfully", CREATED);
        } catch (RuntimeException ex) {
            response = new ResponseEntity<>("La creación del menú falló. " + ex.getMessage(), INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @DeleteMapping("/{providerEmail}/{menuName}")
    public ResponseEntity<String> deleteMenu(@PathVariable final String providerEmail, final @PathVariable String menuName) {
        ResponseEntity<String> response;
        try {
            menuService.deleteMenu(providerEmail, menuName);
            response = new ResponseEntity<>("Menú borrado exitosamente", HttpStatus.valueOf(204));
        } catch (RuntimeException ex) {
            response = new ResponseEntity<>("No se pudo borrar el menú " + ex.getMessage(), INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<String> modifyMenu(final @RequestBody MenuDTO menuDTO) {
        ResponseEntity<String> response;
        try {
            menuService.updateMenu(menuDTO);
            response = new ResponseEntity<>("Menú actualizado exitosamente", HttpStatus.valueOf(204));
        } catch (RuntimeException ex) {
            response = new ResponseEntity<>("No se pudo actualizar el menú. " + ex.getMessage(), INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @GetMapping("/price")
    public ResponseEntity<Page<Menu>> getBetweenMinPriceAndMaxPrice(@RequestParam final int minPrice, @RequestParam final int maxPrice, @RequestParam final int page, @RequestParam final int itemsPerPage) {
        Page<Menu> menus = menuService.getBetweenMinPriceAndMaxPrice(minPrice, maxPrice, page, itemsPerPage);
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/rank")
    public ResponseEntity<List<Menu>> getBetweenMinRankAndMaxRank(final @RequestParam Integer minRank, final @RequestParam Integer maxRank) {
        List<Menu> menus = menuService.getBetweenMinRankAndMaxRank(minRank, maxRank);
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/category")
    public ResponseEntity<Page<Menu>> getByCategory(final @RequestParam Category category, final @RequestParam int page, final @RequestParam int itemsPerPage) {
        Page<Menu> menus = menuService.getByCategory(category, page, itemsPerPage);
        return ResponseEntity.ok(menus);
    }

}
