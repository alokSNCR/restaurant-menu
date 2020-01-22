package com.presto.menu.model;

import java.io.Serializable;

/**
 * @author alok.kumarr
 * @since 1.0
 */
public class Category implements Serializable {

  public Category(){}
  public Category(String name) {
    this.name = name;
  }

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
