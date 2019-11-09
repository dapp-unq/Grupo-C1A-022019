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

    @Transactional
    @Modifying
    @Query("update Provider p set p.name = :name, p.logo = :logo, p.city = :city, p.location = :location, " +
            "p.description = :description, p.website = :website, p.phoneNumber = :phoneNumber, p.openingHoursDays = :openingHoursDays," +
            " p.deliveryCities = :deliveryCities, p.currentMenus = :currentMenus, p.balance = :balance" +
            " where p.email = :email")
    void updateProvider(final @Param("email") String email, final @Param("name") String name, final @Param("logo") String logo, final @Param("city") City city,
                        final @Param("location") String location, final @Param("description") String description, final @Param("website") String website,
                        final @Param("phoneNumber") String phoneNumber, final @Param("openingHoursDays") List<ServiceHours> openingHoursDays,
                        final @Param("deliveryCities") List<City> deliveryCities, final @Param("currentMenus") List<Menu> currentMenus,
                        final @Param("balance") BigDecimal balance);
}
