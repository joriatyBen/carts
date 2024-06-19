package works.weave.socks.cart.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import works.weave.socks.cart.entities.Cart;

import java.util.List;

@Transactional
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByCustomerId(@Param("customer_id") int id);

    @Modifying
    @Query("delete from Cart c where c.customerId = ?1")
    void deleteByCustomerId(int customerId);
}

