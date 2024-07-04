package works.weave.socks.cart.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import works.weave.socks.cart.entities.ItemDTO;
import works.weave.socks.cart.item.FoundItem;
import works.weave.socks.cart.repositories.CartItemsRepository;
import works.weave.socks.cart.repositories.CartRepository;
import works.weave.socks.cart.repositories.CustomerRepository;
import works.weave.socks.cart.repositories.ItemRepository;
import works.weave.socks.cart.item.ItemResource;

import java.time.LocalTime;

import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = "/carts/{customerId:.*}/items")
public class ItemsController {
  private final Logger LOG = getLogger(getClass());

  @Autowired
  ItemRepository itemRepository;

  @Autowired
  CartRepository cartRepository;

  @Autowired
  CartItemsRepository cartItemsRepository;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  CartsController cartsController;

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/{itemId:.*}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
  public ItemDTO get(@PathVariable int customerId, @PathVariable int itemId) {
    return new FoundItem(() -> getItems(customerId), () -> new ItemDTO(itemId), () -> null).get();
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
  public List<ItemDTO> getItems(@PathVariable int customerId) {
    return cartsController.getOrdersByCustomerId(customerId).getBody();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
  public List<ItemDTO> addToCart(@PathVariable int customerId, @RequestBody ItemDTO.CheckoutRequest checkoutRequest) {
    LocalTime startTime = LocalTime.now(); // use this somehow

    // multiple orders can be assigned to one customer number - the check for an existing item is therefore useless
    //FoundItem foundItem = new FoundItem(() -> getItems(customerId), () -> null, () -> checkoutRequest);

    Optional.of(customerRepository.findById(customerId).isEmpty())
            .filter(isEmpty -> isEmpty)
            .ifPresent(isEmpty -> {
              LOG.warn("Customer not found with customerId: {}", customerId);
            });


    /* insert into oder_details(Cart), oder_items(CartItems) and update/insert customer
     * subsequently return the incoming request mapped to an itemDTO */
    return new ItemResource(
              cartRepository,
              cartItemsRepository,
              customerRepository,
              itemRepository,
              () -> checkoutRequest).create().get();
  }

//  @ResponseStatus(HttpStatus.ACCEPTED)
//  @RequestMapping(value = "/{itemId:.*}", method = RequestMethod.DELETE)
//  public void removeItem(@PathVariable String customerId, @PathVariable String itemId) {
//    FoundItem foundItem = new FoundItem(() -> getItems(customerId), () -> new Item(itemId));
//    Item item = foundItem.get();
//
//    LOG.debug("Removing item from cart: " + item);
//    new CartResource(cartDAO, customerId).contents().get().delete(() -> item).run();
//
//    LOG.debug("Removing item from repository: " + item);
//    new ItemResource(itemDAO, () -> item).destroy().run();
//  }
//
//  @ResponseStatus(HttpStatus.ACCEPTED)
//  @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PATCH)
//  public void updateItem(@PathVariable String customerId, @RequestBody Item item) {
//    // Merge old and new items
//    ItemResource itemResource = new ItemResource(itemDAO, () -> get(customerId, item.itemId()));
//    LOG.debug("Merging item in cart for user: " + customerId + ", " + item);
//    itemResource.merge(item).run();
//  }
}
