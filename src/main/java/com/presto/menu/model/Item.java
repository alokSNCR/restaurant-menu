package com.presto.menu.model;

import java.io.Serializable;

public class Item implements Serializable {

  private String name;
  private String category;

  public Item(){}

  public Item(String name, String category) {
    this.name = name;
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}
