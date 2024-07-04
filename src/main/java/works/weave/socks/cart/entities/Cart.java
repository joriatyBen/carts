package works.weave.socks.cart.entities;

import lombok.*;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "order_details")
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_details_id_seq")
  @SequenceGenerator(name = "order_details_id_seq", sequenceName = "order_details_id_seq", allocationSize = 1)
  @Column(name = "id")
  private int id;

  @Column(name = "timestamp_created")
  private Date orderRequest;

  @Column(name = "total_products")
  private String items;

  @Column(name = "total_price")
  private int totalOrderPrice;

  @Column(name = "customer_id")
  private int customerId;

  @Column(name = "order_state")
  private String orderState;

  public Cart() {
  }

  public Cart(Date orderRequest, String items, int totalOrderPrice, int customerId, String orderState) {
    this.orderRequest = orderRequest;
    this.items = items;
    this.totalOrderPrice = totalOrderPrice;
    this.customerId = customerId;
    this.orderState = orderState;
  }

  public Cart(int customerId) {
  }

  public String contents() {
    return items;
  }

  // for fn merge needed only, which is currently not implemented
  public Cart add(Item item) {
    return null;
  }

  public Cart remove(Item item) {
    return null;
  }
}
