package ar.edu.unq.desapp.grupoa.webservice;

import ar.edu.unq.desapp.grupoa.service.UserService;
import ar.edu.unq.desapp.grupoa.service.dto.UserDTO;
import ar.edu.unq.desapp.grupoa.service.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        ResponseEntity response;
        try {
            userService.createUser(userDTO);
            response = new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } catch (final DataIntegrityViolationException ex) {
            response = new ResponseEntity<>("The user with this email already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(final @PathVariable String email) {
        userService.deleteUser(email);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.valueOf(204));
    }

    @PutMapping
    public ResponseEntity<String> updateUser(final @RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return new ResponseEntity<>("Currency updated successfully", HttpStatus.valueOf(204));
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUser(final @PathVariable String email) {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok(userService.getUserByEmail(email));
        } catch (final UserNotFoundException ex) {
            response = new ResponseEntity<>("User not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping("/{email}/{currency}")
    public ResponseEntity<String> modifyCurrency(final @PathVariable String email, final @PathVariable BigDecimal currency) {
        userService.modifyCurrency(currency, email);
        return new ResponseEntity<>("Currency updated successfully", HttpStatus.valueOf(204));
    }

}
