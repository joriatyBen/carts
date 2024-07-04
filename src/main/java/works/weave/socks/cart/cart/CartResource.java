package works.weave.socks.cart.cart;

import lombok.AllArgsConstructor;
import works.weave.socks.cart.entities.Cart;
import works.weave.socks.cart.repositories.*;

import java.util.function.Supplier;

@AllArgsConstructor
public class CartResource implements Resource<Cart> {
  private final CartRepository cartRepository;
  private final CartItemsRepository cartItemsRepository;
  private final int customerId;

  @Override
  public Runnable destroy() {
    return () -> {
      cartRepository.findByCustomerId(customerId)
              .stream().map(Cart::getId).toList()
              .forEach(cartItemsRepository::deleteByOrderId);
      cartRepository.deleteByCustomerId(customerId);
    };
  }

  @Override
  public Supplier<Cart> value() {
    return null;
  }

  @Override
  public Supplier<Cart> create() {
    return () -> cartRepository.save(new Cart(customerId));
  }
}
