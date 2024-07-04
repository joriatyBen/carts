package works.weave.socks.cart.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "customers")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_id_seq")
  @SequenceGenerator(name = "customers_id_seq", sequenceName = "customers_id_seq", allocationSize = 1)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;

  @Column(name = "address")
  private String address;

  @Column(name = "city")
  private String city;

  @Column(name = "pin")
  private String pin;

  @Column(name = "last_ordered")
  private Date lastOrdered;

  public Customer() {
  }

  public Customer(String name, String email, String phone, String address, String city, String pin, Date lastOrdered) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.city = city;
    this.pin = pin;
    this.lastOrdered = lastOrdered;
  }
}
