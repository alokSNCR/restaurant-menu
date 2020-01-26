package com.presto.menu.model.response;

import com.presto.menu.model.JoeMenuRequest;
import com.presto.menu.repository.entity.Category;
import com.presto.menu.repository.entity.Item;

import java.io.Serializable;
import java.util.List;

/**
 * @author alok.kumarr
 * @since 1.0
 */
public class Response implements Serializable {

  private String message;
  private List<Item> items;
  private List<Category> categories;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }
}
