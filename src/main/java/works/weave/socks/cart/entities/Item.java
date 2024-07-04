package works.weave.socks.cart.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "product_details")
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_details_id_seq")
  @SequenceGenerator(name = "product_details_id_seq", sequenceName = "product_details_id_seq", allocationSize = 1)
  @Column(name = "id")
  private int id;

  @Column(name = "article_number")
  private String articleNumer;

  @Column(name = "name")
  private String name;

  @Column(name = "image_source")
  private String imageSource;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "sellers_price")
  private int unitPrice;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Item item = (Item) o;

    return Objects.equals(articleNumer, item.articleNumer);
  }
}
