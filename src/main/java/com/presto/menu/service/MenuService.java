package com.presto.menu.service;


import com.presto.menu.model.JoeMenuRequest;

public interface MenuService {
  boolean saveMenu(JoeMenuRequest menuRequest);
  Object getItems();
  Object getCategories();
}
