package ar.edu.unq.desapp.grupoa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@NoArgsConstructor
public class CurrentOrder {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @OneToOne(fetch = LAZY, cascade = ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User client;
    @OneToMany(fetch = LAZY, cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "orders_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Order> orders = new ArrayList<>();

    public CurrentOrder(final User client, final Order newOrder) {
        this.client = client;
        addOrder(newOrder);
    }

    public CurrentOrder(final User client, final List<Order> orders) {
        this.client = client;
        orders.addAll(orders);
    }

    public Boolean hasUser(final User aUser) {
        return this.client.equals(aUser);
    }

    public void addOrder(final Order newOrder) {
        this.orders.add(newOrder);
        client.addHistoryOrder(newOrder);
    }

    @PreRemove
    public void preRemove() {
        orders = emptyList();
        client = null;
    }

}
