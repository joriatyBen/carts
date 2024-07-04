package works.weave.socks.cart.itemDTO;

import lombok.AllArgsConstructor;
import works.weave.socks.cart.action.FirstResultOrDefault;
import works.weave.socks.cart.entities.Cart;
import works.weave.socks.cart.entities.ItemDTO;
import works.weave.socks.cart.repositories.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@AllArgsConstructor
public class ItemDTOResource implements Resource<List<ItemDTO>> {
  private final CartRepository cartRepository;
  private final CartItemsRepository cartItemsRepository;
  private final ItemRepository itemRepository;
  private final int customerId;

  @Override
  public Supplier<List<ItemDTO>> create() {
    return null;
  }

  @Override
  public Supplier<List<ItemDTO>> value() {
    return new FirstResultOrDefault<>(
            cartRepository.findByCustomerId(customerId)
                    .stream()
                    .map(Cart::getId)
                    .map(cartItemsRepository::findByOrderId)
                    .flatMap(List::stream)
                    .map(item -> new ItemDTO(
                            Optional.of(customerId),
                            Optional.of(item.getOrderId()),
                            item.getItemId(),
                            item.getItemQuantity(),
                            itemRepository.findById(item.getItemId()).getUnitPrice())
                    )
                    .toList(),
            () -> null);
  }
}
