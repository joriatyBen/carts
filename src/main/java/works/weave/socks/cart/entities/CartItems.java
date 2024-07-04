package works.weave.socks.cart.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "order_items")
public class CartItems {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_items_id_seq")
  @SequenceGenerator(name = "order_items_id_seq", sequenceName = "order_items_id_seq", allocationSize = 1)

  @Column(name = "id")
  private int id;

  @Column(name = "order_id")
  private int orderId;

  @Column(name = "product_id")
  private int itemId;

  @Column(name = "order_quantity")
  private int itemQuantity;

  @Column(name = "timestamp_created")
  private Date orderCreated;

  public CartItems() {
  }

  public CartItems(int orderId, int itemId, int itemQuantity, Date orderCreated) {
    this.orderId = orderId;
    this.itemId = itemId;
    this.itemQuantity = itemQuantity;
    this.orderCreated = orderCreated;
  }
}
