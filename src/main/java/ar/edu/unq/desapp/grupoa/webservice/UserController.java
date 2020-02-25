package ar.edu.unq.desapp.grupoa.webservice;

import ar.edu.unq.desapp.grupoa.service.UserService;
import ar.edu.unq.desapp.grupoa.service.dto.UserDTO;
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
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@CrossOrigin
@RequestMapping("/rest/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(final @RequestBody UserDTO userDTO) {
        ResponseEntity<String> response;
        try {
            userService.createUser(userDTO);
            response = new ResponseEntity<>("Usuario creado exitosamente.", HttpStatus.CREATED);
        } catch (final RuntimeException ex) {
            response = new ResponseEntity<>("La creación de usuario falló. " + ex.getMessage(), INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(final @PathVariable String email) {
        ResponseEntity<String> response;
        try {
            userService.deleteUser(email);
            response = new ResponseEntity<>("Usuario borrado existosamente", HttpStatus.valueOf(204));
        } catch (RuntimeException ex) {
            response = new ResponseEntity<>("Borrado de usuario falló. " + ex.getMessage(), INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<String> updateUser(final @RequestBody UserDTO userDTO) {
        ResponseEntity<String> response;
        try {
            userService.updateUser(userDTO);
            response = new ResponseEntity<>("Currency updated successfully", HttpStatus.valueOf(204));
        } catch (final RuntimeException ex) {
            response = new ResponseEntity<>("No se pudo actualizar el usuario. " + ex.getMessage(), INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @GetMapping("/{email}")
    public ResponseEntity getUser(final @PathVariable String email) {
        ResponseEntity<UserDTO> response;
        try {
            response = ResponseEntity.ok(userService.getUserByEmail(email));
        } catch (final RuntimeException ex) {
            response = new ResponseEntity(ex.getMessage(), INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping("/{email}/{currency}")
    public ResponseEntity<String> modifyCurrency(final @PathVariable String email, final @PathVariable BigDecimal currency) {
        ResponseEntity<String> response;
        try {
            userService.modifyCurrency(currency, email);
            response = new ResponseEntity<>("Dinero actualizado exitosamente", HttpStatus.valueOf(204));
        } catch (RuntimeException ex) {
            response = new ResponseEntity<>("Actualización de dinero falló. " + ex.getMessage(), INTERNAL_SERVER_ERROR);
        }
        return response;
    }

}
