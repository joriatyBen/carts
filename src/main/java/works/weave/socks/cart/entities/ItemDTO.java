package works.weave.socks.cart.entities;

import lombok.*;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class ItemDTO {
  private Optional<Integer> customerId;
  private Optional<Integer> orderId;
  private int itemId;
  private int itemQuantity;
  private int unitPrice;

  public ItemDTO(int itemId) {
    this.itemId = itemId;
  }

  public ItemDTO(Optional<Integer> customerId, Optional<Integer> orderId, int itemId, int itemQuantity, int unitPrice) {
    this.customerId = customerId;
    this.orderId = orderId;
    this.itemId = itemId;
    this.itemQuantity = itemQuantity;
    this.unitPrice = unitPrice;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  public static class CheckoutRequest {
    private Customer customer;
    private List<CheckoutItem> checkout;
    private int orderTotal;
    private String orderState;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  public static class CheckoutItem {
    private int id;
    private String name;
    private double price;
    private int quantity;
  }
}