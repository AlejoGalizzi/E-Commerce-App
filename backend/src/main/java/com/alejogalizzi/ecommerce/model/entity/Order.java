package com.alejogalizzi.ecommerce.model.entity;

import com.alejogalizzi.ecommerce.model.authorization.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Entity
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Exclude
  @OneToMany(mappedBy = "order")
  private List<Item> items;

  private double totalPrice;

  @Exclude
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public double getTotalPrice() {
    return items.stream().mapToDouble(item -> (double)item.getQuantity()*item.getProduct().getPrice()).sum();
  }
}
