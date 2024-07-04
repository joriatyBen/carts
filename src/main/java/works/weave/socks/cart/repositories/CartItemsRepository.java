package works.weave.socks.cart.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import works.weave.socks.cart.entities.CartItems;

import java.util.List;

@Transactional
public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {
  List<CartItems> findByOrderId(@Param("order_id") int orderId);

  @Modifying
  @Query("delete from CartItems ci where ci.orderId = ?1")
  void deleteByOrderId(int orderId);
}
