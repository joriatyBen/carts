package works.weave.socks.cart.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import works.weave.socks.cart.entities.Cart;
import works.weave.socks.cart.repositories.CartRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/carts")
public class CartsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CartRepository cartRepository;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Cart> getOrderByCustomerId (@PathVariable int customerId) {
        try {
            List<Cart> orderData = cartRepository.findByCustomerId(customerId);
            return new ResponseEntity<>(orderData.get(customerId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteOrderByCustomerId (@PathVariable int customerId) {
        try {
            cartRepository.deleteAllByIdInBatch(
                cartRepository.findByCustomerId(customerId)
                    .stream()
                    .map(Cart::getCustomerId)
                    .collect(Collectors.toList())
            );
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
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
