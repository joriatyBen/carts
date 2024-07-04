package works.weave.socks.cart.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import works.weave.socks.cart.cart.CartResource;
import works.weave.socks.cart.itemDTO.ItemDTOResource;
import works.weave.socks.cart.entities.*;
import works.weave.socks.cart.repositories.*;

import java.util.List;

@RestController
@RequestMapping(path = "/carts")
public class CartsController {
  private final Logger LOG = LoggerFactory.getLogger(this.getClass());

  @Autowired
  CartRepository cartRepository;

  @Autowired
  CartItemsRepository cartItemsRepository;

  @Autowired
  ItemRepository itemRepository;

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
  public ResponseEntity<List<ItemDTO>> getOrdersByCustomerId(@PathVariable int customerId) {
    try {
      List<ItemDTO> orderItems = new ItemDTOResource(
              cartRepository,
              cartItemsRepository,
              itemRepository,
              customerId).value().get();
      LOG.debug("Found {} ordered items for customer {} in db.", orderItems.size(), customerId);
      return new ResponseEntity<>(orderItems, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> deleteOrderByCustomerId(@PathVariable int customerId) {
    try {
      new CartResource(cartRepository, cartItemsRepository, customerId).destroy().run();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // Todo: Implement this
  // @ResponseStatus(HttpStatus.ACCEPTED)
  // @RequestMapping(value = "/{customerId}/merge", method = RequestMethod.GET)
  // public void mergeCarts(@PathVariable String customerId, @RequestParam(value = "sessionId") String sessionId) {
  //     logger.debug("Merge carts request received for ids: " + customerId + " and " + sessionId);
  //     CartResource sessionCart = new CartResource(cartDAO, sessionId);
  //     CartResource customerCart = new CartResource(cartDAO, customerId);
  //     customerCart.merge(sessionCart.value().get()).run();
  //     delete(sessionId);
  // }
}
