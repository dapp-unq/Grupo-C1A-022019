package ar.edu.unq.desapp.grupoa.persistence;

import ar.edu.unq.desapp.grupoa.model.Menu;
import ar.edu.unq.desapp.grupoa.model.Provider;
import ar.edu.unq.desapp.grupoa.model.ServiceHours;
import ar.edu.unq.desapp.grupoa.model.enums.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    Optional<Provider> findByEmail(final String email);

    @Transactional
    void deleteByEmail(final String email);
}
