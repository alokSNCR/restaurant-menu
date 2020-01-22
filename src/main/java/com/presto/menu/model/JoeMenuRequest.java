package com.presto.menu.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author alok.kumarr
 * @since 1.0
 */
public class JoeMenuRequest implements Serializable {

  private String restaurant;
  private List<Item> item;
  private List<Category> category;

  public String getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(String restaurant) {
    this.restaurant = restaurant;
  }

  public List<Item> getItem() {
    return item;
  }

  public void setItem(List<Item> item) {
    this.item = item;
  }

  public List<Category> getCategory() {
    return category;
  }

  public void setCategory(List<Category> category) {
    this.category = category;
  }
}
