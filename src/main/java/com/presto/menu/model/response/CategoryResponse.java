package com.presto.menu.model.response;

import java.io.Serializable;

public class CategoryResponse implements Serializable {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
