package com.presto.menu.service.impl;

import com.presto.menu.model.JoeMenuRequest;
import com.presto.menu.repository.CategoryItemRepository;
import com.presto.menu.repository.CategoryRepository;
import com.presto.menu.repository.ItemRepository;
import com.presto.menu.repository.entity.CategoryItems;
import com.presto.menu.repository.entity.Item;
import com.presto.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

  @Autowired
  private ItemRepository itemRepository;
  @Autowired
  private CategoryRepository categoryRepository;
  @Autowired
  private CategoryItemRepository categoryItemRepository;


  @Override
  public boolean saveMenu(JoeMenuRequest menuRequest) {
    // clean the table before add menu everyday
    categoryItemRepository.deleteAll();
    categoryRepository.deleteAll();
    itemRepository.deleteAll();

    menuRequest.getItem().stream().forEach(item -> {
      com.presto.menu.repository.entity.Item it = new com.presto.menu.repository.entity.Item();
      it.setName(item.getName());
      itemRepository.saveAndFlush(it);
    });

    menuRequest.getCategory().stream().forEach(category -> {
      com.presto.menu.repository.entity.Category cat = new com.presto.menu.repository.entity.Category();
      cat.setName(category.getName());
      categoryRepository.saveAndFlush(cat);
    });


    // save category item table
    List<Item> items = itemRepository.findAll();
    List<com.presto.menu.repository.entity.Category> categoryList = categoryRepository.findAll();
    menuRequest.getItem().stream().forEach(it -> {
      CategoryItems categoryItems = new CategoryItems();
      Long itemId = items.stream().filter(item ->
          item.getName().equals(it.getName())).findAny().get().getId();
      categoryItems.setItemId(itemId);
      Long categoryId = categoryList.stream().filter(cat ->
          cat.getName().equals(it.getCategory())).findAny().get().getId();
      categoryItems.setCategoryId(categoryId);
    });

    return true;
  }
}
