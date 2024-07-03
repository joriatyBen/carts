package works.weave.socks.cart.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import works.weave.socks.cart.entities.Customer;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
  default Customer updateOrInsert(Customer customer) {
    return save(customer);
  }

  Customer findCustomerByNameAndEmail(String name, String email);
}
