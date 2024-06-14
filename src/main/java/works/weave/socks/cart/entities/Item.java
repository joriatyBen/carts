// package works.weave.socks.cart.entities;
//
// import jakarta.persistence.*;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;
//
// import java.io.Serializable;
// import java.util.Objects;
//
// @Entity
// @Getter
// @Setter
// @NoArgsConstructor
// @Table(name = "product-details")
// public class Item {
//
//
//     @jakarta.persistence.Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private int id;
//
//     @Column(name = "article_number")
//     private String articleNumer;
//
//     @Column(name = "name")
//     private String name;
//
//     @Column(name = "image_source")
//     private String imageSource;
//
//     @Column(name = "quantity")
//     private int quantity;
//
//     @Column(name = "sellers_price")
//     private int unitPrice;
//
//     public Item(String articleNumer) {
//         this.articleNumer = articleNumer;
//     }
//
//     @Override
//     public boolean equals(Object o) {
//         if (this == o) return true;
//         if (o == null || getClass() != o.getClass()) return false;
//
//         Item item = (Item) o;
//
//         return Objects.equals(articleNumer, item.articleNumer);
//     }
// }
