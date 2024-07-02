package works.weave.socks.cart.itemDTO;

import java.util.function.Supplier;

public interface Resource<T> {
  Supplier<T> create();

  Supplier<T> value();
}
