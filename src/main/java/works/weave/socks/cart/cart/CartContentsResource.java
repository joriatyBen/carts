package works.weave.socks.cart.cart;

import lombok.AllArgsConstructor;
import works.weave.socks.cart.entities.Item;

import java.util.function.Supplier;

import org.slf4j.Logger;
import works.weave.socks.cart.entities.Cart;
import works.weave.socks.cart.repositories.CartRepository;

import static org.slf4j.LoggerFactory.getLogger;

@AllArgsConstructor
public class CartContentsResource implements Contents<Item> {
  private final Logger LOG = getLogger(getClass());

  private final CartRepository cartRepository;
  private final Supplier<Resource<Cart>> parent;

  @Override
  public Runnable add(Supplier<Item> item) {
    return () -> {
      LOG.debug("Adding for user: {}, {}", parent.get().value().get().toString(), item.get());
      cartRepository.save(parentCart().add(item.get())); //Cart entity
    };
  }

  private Cart parentCart() {
    return parent.get().value().get();
  }
}
