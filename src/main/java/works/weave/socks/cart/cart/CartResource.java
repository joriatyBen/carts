package works.weave.socks.cart.cart;

import lombok.AllArgsConstructor;
import works.weave.socks.cart.entities.Cart;
import works.weave.socks.cart.repositories.*;

import java.util.function.Supplier;

@AllArgsConstructor
public class CartResource implements Resource<Cart>, HasContents<CartContentsResource> {
  private final CartRepository cartRepository;
  private final CartItemsRepository cartItemsRepository;

  @Override
  public Runnable destroy() {
    return () -> {
      cartRepository.findByCustomerId(value().get().getCustomerId())
              .stream().map(Cart::getId).toList()
              .forEach(cartItemsRepository::deleteByOrderId);
      cartRepository.deleteByCustomerId(value().get().getCustomerId());
    };
  }

  @Override
  public Supplier<Cart> value() {
    return null;
  }

  @Override
  public Supplier<CartContentsResource> contents() {
    return null;
  }
}
