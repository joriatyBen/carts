package works.weave.socks.cart.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "timestamp_order_request")
    private Date orderRequest;

    @Column(name = "product_sum")
    private String products;

    @Column(name = "total_order")
    private int orderPrice;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "order_state")
    private String state;


    public Cart(int customerId) {
        this.customerId = customerId;
    }

    public String contents() {
        return products;
    }

    // That functionality is not implemented in the backend
    // public Cart add(Item item) {
    //     items.add(item);
    //     return this;
    // }
    //
    // public Cart remove(Item item) {
    //     items.remove(item);
    //     return this;
    // }
}
