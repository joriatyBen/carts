package works.weave.socks.cart.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class FirstResultOrDefault<T> implements Supplier<List<T>> {
  private final Collection<T> collection;
  private final Supplier<T> nonePresent;

  public FirstResultOrDefault(final Collection<T> collection, final Supplier<T> nonePresent) {
    this.collection = collection;
    this.nonePresent = nonePresent;
  }

  @Override
  public List<T> get() {
    if (collection.isEmpty()) {
      return List.of(nonePresent.get());
    }
    return new ArrayList<>(collection);
  }
}
