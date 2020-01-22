package com.presto.menu.model.response;

import com.presto.menu.model.JoeMenuRequest;

import java.io.Serializable;

/**
 * @author alok.kumarr
 * @since 1.0
 */
public class Response implements Serializable {

  private JoeMenuRequest menu;
  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public JoeMenuRequest getMenu() {
    return menu;
  }

  public void setMenu(JoeMenuRequest menu) {
    this.menu = menu;
  }
}
