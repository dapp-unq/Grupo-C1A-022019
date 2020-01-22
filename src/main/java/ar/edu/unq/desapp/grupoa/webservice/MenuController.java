package ar.edu.unq.desapp.grupoa.webservice;

import ar.edu.unq.desapp.grupoa.model.Menu;
import ar.edu.unq.desapp.grupoa.model.enums.Category;
import ar.edu.unq.desapp.grupoa.service.MenuService;
import ar.edu.unq.desapp.grupoa.service.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
        menuService.createMenu(menuDTO);
        return new ResponseEntity<>("Menu created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{providerEmail}/{menuName}")
    public ResponseEntity<String> deleteMenu(@PathVariable final String providerEmail, final @PathVariable String menuName) {
        menuService.deleteMenu(providerEmail, menuName);
        return new ResponseEntity<>("Menu deleted successfully", HttpStatus.valueOf(204));
    }

    @PutMapping
    public ResponseEntity<String> modifyMenu(final @RequestBody MenuDTO menuDTO) {
        menuService.updateMenu(menuDTO);
        return new ResponseEntity<>("Menu updated successfully", HttpStatus.valueOf(204));
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getBetweenMinPriceAndMaxPrice(final @RequestParam Integer minPrice, final @RequestParam Integer maxPrice) {
        List<Menu> menus = menuService.getBetweenMinPriceAndMaxPrice(minPrice, maxPrice);
        return ResponseEntity.ok(menus);
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getBetweenMinRankAndMaxRank(final @RequestParam Integer minRank, final @RequestParam Integer maxRank) {
        List<Menu> menus = menuService.getBetweenMinRankAndMaxRank(minRank, maxRank);
        return ResponseEntity.ok(menus);
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getBetweenMinRankAndMaxRank(final @RequestParam Category category) {
        List<Menu> menus = menuService.getByCategory(category);
        return ResponseEntity.ok(menus);
    }

}
