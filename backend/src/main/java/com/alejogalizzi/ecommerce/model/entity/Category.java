package com.alejogalizzi.ecommerce.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "categories")
@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  @OneToMany(mappedBy = "category")
  private List<Product> products;

  @CreatedDate
  @Temporal(TemporalType.DATE)
  private Date createdAt;
}
