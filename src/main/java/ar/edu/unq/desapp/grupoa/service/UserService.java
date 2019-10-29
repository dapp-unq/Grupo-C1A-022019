package ar.edu.unq.desapp.grupoa.service;

import ar.edu.unq.desapp.grupoa.model.User;
import ar.edu.unq.desapp.grupoa.persistence.UserRepository;
import ar.edu.unq.desapp.grupoa.service.dto.UserDTO;
import ar.edu.unq.desapp.grupoa.service.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(final UserDTO userDTO) {
        User newUser = userDtoToUser(userDTO);
        userRepository.save(newUser);
    }

    private User userDtoToUser(final UserDTO userDTO) {
        return new User(userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(), userDTO.getPhoneNumber(), userDTO.getLocation());
    }

    private UserDTO userToUserDTO(final User user) {
        return new UserDTO(user.getName(), user.getSurname(), user.getEmail(), user.getPhoneNumber(), user.getLocation(), user.getOrderHistory(), user.getBalance());
    }

    public void modifyCurrency(final BigDecimal charge, final String email){
        userRepository.updateCurrency(charge, email);
    }

    public UserDTO getUserByEmail(String email) {
        User fetchedUser = userRepository.findById(email).orElseThrow(UserNotFoundException::new);
        return userToUserDTO(fetchedUser);
    }

    public void deleteUser(String email) {
        userRepository.deleteById(email);
    }
}
