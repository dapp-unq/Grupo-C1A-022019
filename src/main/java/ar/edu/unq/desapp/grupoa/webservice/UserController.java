package ar.edu.unq.desapp.grupoa.webservice;

import ar.edu.unq.desapp.grupoa.service.UserService;
import ar.edu.unq.desapp.grupoa.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/rest/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(final @RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUser(final @PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PutMapping("/{email}/{currency}")
    public ResponseEntity<String> modifyCurrency(final @PathVariable String email, final @PathVariable BigDecimal currency) {
        userService.modifyCurrency(currency, email);
        return new ResponseEntity<>("Currency updated successfully", HttpStatus.valueOf(204));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(final @PathVariable String email){
        userService.deleteUser(email);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.valueOf(204));
    }

}
