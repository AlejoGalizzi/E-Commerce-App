package com.alejogalizzi.ecommerce.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "products")
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;

  private String description;

  private BigDecimal price;

  private int stock;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @OneToOne(cascade = CascadeType.ALL)
  private Image image;

  @CreatedDate
  @Temporal(TemporalType.DATE)
  private Date createdAt;

  @UpdateTimestamp
  @Temporal(TemporalType.DATE)
  private Date updatedAt;
}
