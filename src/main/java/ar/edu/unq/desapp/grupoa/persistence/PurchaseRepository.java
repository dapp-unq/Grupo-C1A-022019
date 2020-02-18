package ar.edu.unq.desapp.grupoa.persistence;

import ar.edu.unq.desapp.grupoa.model.Order;
import ar.edu.unq.desapp.grupoa.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByStatus(final Status status);

    List<Order> findAllByMenuIdAndStatus(final Long id, final Status status);

}
