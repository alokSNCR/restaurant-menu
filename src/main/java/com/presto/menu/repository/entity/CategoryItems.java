package com.presto.menu.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY_ITEMS")
public class CategoryItems {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long Id;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne
  @JoinColumn(name = "ITEM_ID")
  private Item item;

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }
}
