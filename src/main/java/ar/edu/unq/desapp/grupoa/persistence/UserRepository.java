package ar.edu.unq.desapp.grupoa.persistence;

import ar.edu.unq.desapp.grupoa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    @Modifying
    @Query("update User u set u.balance = u.balance + :currency where u.email = :email")
    void updateCurrency(final @Param("currency") BigDecimal currency, final @Param("email") String email);

    @Transactional
    @Modifying
    @Query("update User u set u.balance = :balance, u.name = :name, u.surname = :surname, u.phoneNumber = :phoneNumber, " +
            "u.location = :location where u.email = :email")
    void updateUser(final @Param("email") String email, final @Param("name") String name, final @Param("surname") String surname,
                    final @Param("phoneNumber") String phoneNumber, final @Param("location") String location,
                    final @Param("balance") BigDecimal balance);

    @Transactional
    void deleteByEmail(final String email);

    Optional<User> findByEmail(final String email);
}
