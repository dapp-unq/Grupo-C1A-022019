package ar.edu.unq.desapp.grupoa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@SequenceGenerator(name="CurrentOrderSeq", sequenceName="CURRENTORDERseq", allocationSize=1)
@NoArgsConstructor
public class CurrentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CurrentOrderSeq")
    private Long id;
    @OneToOne
    private User client;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private List<Order> orders = new ArrayList<>();

    public CurrentOrder(final User client, final Order newOrder) {
        this.client = client;
        addOrder(newOrder);
    }

    public CurrentOrder(final User client, final List<Order> orders) {
        this.client = client;
        this.orders = orders;
    }

    public Boolean hasUser(final User aUser) {
        return this.client.equals(aUser);
    }

    public void addOrder(final Order newOrder) {
        this.orders.add(newOrder);
        client.addHistoryOrder(newOrder);
    }

}
