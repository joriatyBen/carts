package works.weave.socks.cart.cart;

import works.weave.socks.cart.entities.Item;

import java.util.function.Supplier;

public interface Contents<T> {
  Runnable add(Supplier<Item> item);
}
