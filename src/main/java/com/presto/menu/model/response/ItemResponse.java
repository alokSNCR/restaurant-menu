package com.presto.menu.model.response;

import java.io.Serializable;

public class ItemResponse implements Serializable {

  private String name;
  private String categoryName;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }
}
