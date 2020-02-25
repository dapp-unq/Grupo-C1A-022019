package ar.edu.unq.desapp.grupoa.service;

import ar.edu.unq.desapp.grupoa.model.User;
import ar.edu.unq.desapp.grupoa.model.exceptions.UserServiceException;
import ar.edu.unq.desapp.grupoa.persistence.UserRepository;
import ar.edu.unq.desapp.grupoa.service.dto.UserDTO;
import ar.edu.unq.desapp.grupoa.service.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void createUser(final UserDTO userDTO) {
        try {
            final User newUser = converterHelper.userDtoToUser(userDTO);
            userRepository.save(newUser);
            log.info("Created User: {}", newUser);
        } catch (DataIntegrityViolationException ex) {
            throw new UserServiceException("Ya existe un usuario con ese email", ex);
        }
    }

    @Transactional
    public void deleteUser(final String email) {
        userRepository.deleteByEmail(email);
        log.info("Deleted User: {}", email);
    }

    @Transactional
    public void updateUser(final UserDTO userDTO) {
        User user = findUser(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setLocation(userDTO.getLocation());
        user.setBalance(userDTO.getBalance());
        userRepository.save(user);
        log.info("Updated user: {}", userDTO.getEmail());
    }

    @Transactional
    public void updateUserOrders(final User user) {
        userRepository.save(user);
        log.info("Updated orders for user: {}", user.getEmail());
    }

    @Transactional
    public void modifyCurrency(final BigDecimal charge, final String email) {
        userRepository.updateCurrency(charge, email);
    }

    public UserDTO getUserByEmail(final String email) {
        final User fetchedUser = findUser(email);
        return converterHelper.userToUserDTO(fetchedUser);
    }

    public User findUser(final String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("No se encontró al usuario: " + email));
    }

}
