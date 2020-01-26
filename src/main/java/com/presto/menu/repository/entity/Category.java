package com.presto.menu.repository.entity;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "CATEGORIES")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id")
  private Long id;
  private String name;

  @OneToMany(mappedBy = "category")
  Set<CategoryItems> categoryItems;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
