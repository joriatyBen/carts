package works.weave.socks.cart.item;

import lombok.AllArgsConstructor;
import works.weave.socks.cart.entities.Cart;
import works.weave.socks.cart.entities.CartItems;
import works.weave.socks.cart.entities.Customer;
import works.weave.socks.cart.itemDTO.ItemDTOResource;
import works.weave.socks.cart.itemDTO.Resource;
import works.weave.socks.cart.entities.ItemDTO;
import works.weave.socks.cart.repositories.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

@AllArgsConstructor
public class ItemResource implements Resource<List<ItemDTO>> {
  private final CartRepository cartRepository;
  private final CartItemsRepository cartItemsRepository;
  private final CustomerRepository customerRepository;
  private final ItemRepository itemRepository;
  private final int customerId;
  private final Supplier<ItemDTO.CheckoutRequest> checkoutRequest;

  @Override
  public Supplier<List<ItemDTO>> create() {
    long ms = System.currentTimeMillis();
    Date startTime = new Date(ms);

    HashMap<String, Integer> products = new HashMap<>();
    checkoutRequest.get().getCheckout().forEach(entry -> products.put(entry.getName(), entry.getQuantity()));

    StringBuilder productsAsString = new StringBuilder("{");

    for (String key : products.keySet()) {
      productsAsString
              .append(key)
              .append(":")
              .append(products.get(key))
              .append(", ");
    }
    productsAsString.delete(
            productsAsString.length()-2,
            productsAsString.length()).append("}"
    );

    return () -> {
      customerRepository.updateOrInsert(new Customer(
              checkoutRequest.get().getCustomer().getName(),
              checkoutRequest.get().getCustomer().getEmail(),
              checkoutRequest.get().getCustomer().getPhone(),
              checkoutRequest.get().getCustomer().getAddress(),
              checkoutRequest.get().getCustomer().getCity(),
              checkoutRequest.get().getCustomer().getPin(),
              startTime
      ));

      cartRepository.save(new Cart(
              startTime,
              productsAsString.toString(),
              checkoutRequest.get().getOrderTotal(),
              customerRepository.findCustomerByNameAndEmail(
                      checkoutRequest.get().getCustomer().getName(),
                      checkoutRequest.get().getCustomer().getEmail()).getId(),
              checkoutRequest.get().getOrderState()
      ));

      for (ItemDTO.CheckoutItem entry: checkoutRequest.get().getCheckout()) {
        cartItemsRepository.save(new CartItems(
                cartRepository.getCartByCustomerId(
                        customerRepository.findCustomerByNameAndEmail(
                                checkoutRequest.get().getCustomer().getName(),
                                checkoutRequest.get().getCustomer().getEmail()).getId()
                ).getId(),
                entry.getId(),
                entry.getQuantity(),
                startTime
        ));
      }

      return new ItemDTOResource(
              cartRepository,
              cartItemsRepository,
              itemRepository,
              customerRepository.findCustomerByNameAndEmail(
                      checkoutRequest.get().getCustomer().getName(),
                      checkoutRequest.get().getCustomer().getEmail()).getId()).value().get();

    };
  }

  @Override
  public Supplier<List<ItemDTO>> value() {
    return null;
  }
}
