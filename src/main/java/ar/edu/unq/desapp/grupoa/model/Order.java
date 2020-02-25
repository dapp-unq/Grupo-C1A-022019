package ar.edu.unq.desapp.grupoa.model;

import ar.edu.unq.desapp.grupoa.model.enums.DeliveryType;
import ar.edu.unq.desapp.grupoa.model.enums.Status;
import ar.edu.unq.desapp.grupoa.model.exceptions.OrderDateException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static ar.edu.unq.desapp.grupoa.model.enums.Status.CREATED;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NonNull
@Entity
@Table(name = "orders")
@NoArgsConstructor
@EqualsAndHashCode
public class Order {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @ManyToOne(fetch = EAGER, cascade = ALL)
    @JoinColumn(name = "menu_id", nullable = false)
    @Setter
    private Menu menu;
    private String providerEmail;
    private LocalDateTime deliveryDateAndHour;
    private LocalDateTime orderDateAndHour;
    private Integer quantity;
    @Enumerated(STRING)
    private DeliveryType typeDelivery;
    @Enumerated(STRING)
    @Setter
    private Status status;
    private Integer ranking;
    private Integer value;

    public Order(final Menu menu, final String providerEmail, final LocalDateTime deliveryDateAndHour, final LocalDateTime orderDateAndHour,
                 final Integer quantity, final DeliveryType typeDelivery) {
        this.menu = menu;
        this.providerEmail = providerEmail;
        validationDateDeliveryMenuOrdered(orderDateAndHour, deliveryDateAndHour);
        this.deliveryDateAndHour = deliveryDateAndHour;
        this.orderDateAndHour = orderDateAndHour;
        this.quantity = quantity;
        this.typeDelivery = typeDelivery;
        this.status = CREATED;
        this.ranking = 0;
        this.value = this.calculateValue(quantity);
    }

    public void setTypeDelivery(final @NonNull DeliveryType type) {
        this.typeDelivery = type;
    }

    public void setRanking(final @NonNull Integer ranking) {
        this.ranking = ranking;
    }

    public Integer calculateValue(final Integer quantity) {
        return (this.menu.valueForQuantity(quantity) * quantity) + deliveryPrice();
    }

    public Integer deliveryPrice() {
        Integer price = 0;
        if (typeDelivery.equals(DeliveryType.HOME_DELIVERY))
            price = this.menu.getDeliveryPrice();
        return price;
    }

    public void validationDateDeliveryMenuOrdered(final LocalDateTime dateHoursOrder,
                                                  final LocalDateTime dateHoursDelivery) {
        if(dateHoursDelivery.isBefore(LocalDateTime.now()))
            throw new OrderDateException("La fecha de entrega no puede tener una fecha pasada");
        if(dateHoursOrder.isBefore(LocalDateTime.now()))
            throw new OrderDateException("La fecha de orden no puede tener una fecha pasada");
        if (!this.has48HoursBetween(dateHoursOrder, dateHoursDelivery))
            throw new OrderDateException("El pedido debe hacerse al menos 48hs hábiles antes de la entrega del mismo.");
    }

    private Boolean has48HoursBetween(final LocalDateTime from, final LocalDateTime to) {
        LocalDateTime to2DaysBefore = to.minusDays(2L);
        return from.isAfter(to2DaysBefore) && from.isBefore(to);
    }

    @PreRemove
    public void preRemove() {
        menu = null;
    }

}
