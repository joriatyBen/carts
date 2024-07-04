package works.weave.socks.cart.item;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import works.weave.socks.cart.entities.ItemDTO;

import java.util.List;
import java.util.function.Supplier;

import static org.slf4j.LoggerFactory.getLogger;

@AllArgsConstructor
public class FoundItem implements Supplier<ItemDTO> {
  private final Logger LOG = getLogger(getClass());
  private final Supplier<List<ItemDTO>> items;
  private final Supplier<ItemDTO> itemDTO;
  private final Supplier<ItemDTO.CheckoutRequest> checkoutRequest;

  @Override
  public ItemDTO get() {
    return items.get().stream()
                   .filter(listItem -> listItem.getItemId() == itemDTO.get().getItemId())
                   .findFirst()
                   .orElseThrow(() -> new IllegalArgumentException("Cannot find item in cart"));
  }

  public boolean hasItem() {
    boolean present = items.get().stream()
                              .anyMatch(listItem -> checkoutRequest.get().getCheckout().stream()
                                                            .map(ItemDTO.CheckoutItem::getId)
                                                            .anyMatch(checkoutItemId -> checkoutItemId.equals(listItem.getItemId()))
                              );
    LOG.debug("{} item: {}, in: {}", present ? "Found" : "Didn't find", checkoutRequest.get(), items.get());
    return present;
  }
}
