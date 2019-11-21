package ar.edu.unq.desapp.grupoa.model;

import ar.edu.unq.desapp.grupoa.model.enums.DeliveryType;
import ar.edu.unq.desapp.grupoa.model.enums.Status;
import ar.edu.unq.desapp.grupoa.model.exceptions.OrderDateException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@NonNull
@Entity
@Table(name = "orders")
@NoArgsConstructor
@EqualsAndHashCode
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    @Setter
    private Menu menu;
    private String providerEmail;
    private LocalDateTime deliveryDateAndHour;
    private LocalDateTime orderDateAndHour;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private DeliveryType typeDelivery;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Integer ranking;
    private Integer value;

    public Order(final Menu menu, final String providerEmail, final LocalDateTime deliveryDateAndHour, final LocalDateTime orderDateAndHour,
                 final Integer quantity, final DeliveryType typeDelivery, final Status status) {
        this.menu = menu;
        this.providerEmail = providerEmail;
        validationDateDeliveryMenuOrdered(orderDateAndHour, deliveryDateAndHour);
        this.deliveryDateAndHour = deliveryDateAndHour;
        this.orderDateAndHour = orderDateAndHour;
        this.quantity = quantity;
        this.typeDelivery = typeDelivery;
        this.status = status;
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
        if (!this.has48HoursBetween(dateHoursOrder, dateHoursDelivery))
            throw new OrderDateException("El pedido debe hacerse al menos 48hs hábiles antes de la entrega del mismo.");
    }

    private Boolean has48HoursBetween(final LocalDateTime from, final LocalDateTime to) {
        LocalDateTime to2DaysBefore = to.minusDays(2L);
        return from.isAfter(to2DaysBefore) && from.isBefore(to);
    }


}
