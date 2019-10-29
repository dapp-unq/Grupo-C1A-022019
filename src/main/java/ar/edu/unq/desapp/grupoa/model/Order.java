package ar.edu.unq.desapp.grupoa.model;

import ar.edu.unq.desapp.grupoa.model.enums.DeliveryType;
import ar.edu.unq.desapp.grupoa.model.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Getter
@NonNull
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Transient
    private Menu menu;
    private String providerName;
    private LocalDateTime deliveryDateAndHour;
    private LocalDateTime orderDateAndHour;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private DeliveryType typeDelivery;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Integer ranking;
    private Integer value;

    public Order(Menu menu, String providerName, LocalDateTime deliveryDateAndHour, LocalDateTime orderDateAndHour, Integer quantity,
                 DeliveryType typeDelivery, Status status) {
        this.menu = menu;
        this.providerName = providerName;
        this.deliveryDateAndHour = deliveryDateAndHour;
        this.orderDateAndHour = orderDateAndHour;
        this.quantity = quantity;
        this.typeDelivery = typeDelivery;
        this.status = status;
        this.ranking = 0;
        this.value = this.calculateValue(quantity);
    }

    public void setTypeDelivery(@NonNull DeliveryType type) {
        this.typeDelivery = type;
    }

    public void setRanking(@NonNull Integer ranking) {
        this.ranking = ranking;
    }

    public Integer calculateValue(Integer quantity) {
        return (this.menu.valueForQuantity(quantity) * quantity) + deliveryPrice();
    }

    public Integer deliveryPrice() {
        Integer price = 0;
        if (typeDelivery.equals(DeliveryType.HOME_DELIVERY))
            price = this.menu.getDeliveryPrice();
        return price;
    }

}
