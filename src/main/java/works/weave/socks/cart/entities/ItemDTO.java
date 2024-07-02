package works.weave.socks.cart.entities;

import lombok.*;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class ItemDTO {
  private Optional<Integer> customerId;
  private Optional<Integer> orderId;
  private int itemId;
  private int itemQuantity;
  private int unitPrice;

  public ItemDTO(int itemId) {
    this.itemId = itemId;
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