package ar.edu.unq.desapp.grupoa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@NoArgsConstructor
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Offer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
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
