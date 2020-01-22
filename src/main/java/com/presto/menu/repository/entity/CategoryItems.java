package com.presto.menu.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY_ITEMS")
public class CategoryItems {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long Id;

  @Column(name = "ITEM_ID")
  private Long itemId;

  @Column(name = "CATEGORY_ID")
  private Long categoryId;

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public Long getItemId() {
    return itemId;
  }

  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }
}
