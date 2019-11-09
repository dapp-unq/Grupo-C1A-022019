package ar.edu.unq.desapp.grupoa.service;

import ar.edu.unq.desapp.grupoa.model.User;
import ar.edu.unq.desapp.grupoa.persistence.UserRepository;
import ar.edu.unq.desapp.grupoa.service.dto.UserDTO;
import ar.edu.unq.desapp.grupoa.service.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class UserService {

    private UserRepository userRepository;

    private ConverterHelper converterHelper;

    @Autowired
    public UserService(final UserRepository userRepository, final ConverterHelper converterHelper) {
        this.userRepository = userRepository;
        this.converterHelper = converterHelper;
    }

    public void createUser(final UserDTO userDTO) {
        final User newUser = converterHelper.userDtoToUser(userDTO);
        userRepository.save(newUser);
        log.info("Created User: {}", newUser);
    }

    public void deleteUser(final String email) {
        userRepository.deleteByEmail(email);
        log.info("Deleted User: {}", email);
    }

    public void updateUser(final UserDTO userDTO) {
        userRepository.updateUser(userDTO.getEmail(), userDTO.getName(), userDTO.getSurname(), userDTO.getPhoneNumber(),
                userDTO.getLocation(), userDTO.getBalance());
    }

    public void modifyCurrency(final BigDecimal charge, final String email) {
        userRepository.updateCurrency(charge, email);
    }

    public UserDTO getUserByEmail(final String email) {
        final User fetchedUser = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return converterHelper.userToUserDTO(fetchedUser);
    }

}
