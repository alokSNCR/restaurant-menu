package com.presto.menu.repository.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ITEMS")
public class Item{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "item_id", updatable = false, nullable = false)
  private Long id;
  private String name;

  @OneToMany(mappedBy = "item")
  Set<CategoryItems> categoryItems;

  public Item(){}

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
