package ar.edu.unq.desapp.grupoa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer quantity;
    private Integer price;

    public Offer(final Integer quantity, final Integer price) {
        this.quantity = quantity;
        this.price = price;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(final Integer price) {
        this.price = price;
    }

    public Boolean isEffectiveOffer() {
        return (!this.quantity.equals(0) && !this.price.equals(0));
    }

}
