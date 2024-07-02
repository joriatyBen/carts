package works.weave.socks.cart.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import works.weave.socks.cart.entities.Item;

@RepositoryRestResource
@Transactional
public interface ItemRepository extends JpaRepository<Item, String> {
  Item findByName(@Param("name") String name);
  Item findById(@Param("id") int id);



}

