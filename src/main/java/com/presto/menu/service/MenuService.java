package com.presto.menu.service;

import com.presto.menu.model.JoeMenuRequest;
import com.presto.menu.model.response.CategoryResponse;
import com.presto.menu.model.response.ItemResponse;
import com.presto.menu.model.response.Response;

import java.util.List;

public interface MenuService {

  Response addMenu(JoeMenuRequest menuRequest);

  List<ItemResponse> getItems(JoeMenuRequest menuRequest);

  List<CategoryResponse> getCategories(JoeMenuRequest menuRequest);

}
