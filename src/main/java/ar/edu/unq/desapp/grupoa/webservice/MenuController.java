package ar.edu.unq.desapp.grupoa.webservice;

import ar.edu.unq.desapp.grupoa.service.MenuService;
import ar.edu.unq.desapp.grupoa.service.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/menu")
public class MenuController {

    private MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity createMenu(final @RequestBody MenuDTO menuDTO) {
        menuService.createMenu(menuDTO);
        return new ResponseEntity<>("Menu created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{providerEmail}/{menuName}")
    public ResponseEntity deleteMenu(@PathVariable final String providerEmail, final @PathVariable String menuName) {
        menuService.deleteMenu(providerEmail, menuName);
        return new ResponseEntity<>("Menu deleted successfully", HttpStatus.valueOf(204));
    }

    @PutMapping
    public ResponseEntity modifyMenu(final @RequestBody MenuDTO menuDTO) {
        menuService.updateMenu(menuDTO);
        return new ResponseEntity<>("Menu updated successfully", HttpStatus.valueOf(204));
    }


}
