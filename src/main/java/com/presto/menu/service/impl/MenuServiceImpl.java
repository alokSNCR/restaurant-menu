package com.presto.menu.service.impl;

import com.presto.menu.model.JoeMenuRequest;
import com.presto.menu.repository.CategoryItemRepository;
import com.presto.menu.repository.CategoryRepository;
import com.presto.menu.repository.ItemRepository;
import com.presto.menu.repository.entity.Category;
import com.presto.menu.repository.entity.CategoryItems;
import com.presto.menu.repository.entity.Item;
import com.presto.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    List<CategoryItems> categoryItems = new ArrayList<>();
    menuRequest.getItem().stream().forEach(request -> {
      CategoryItems categoryItem = new CategoryItems();

      Optional<Item> item = items.stream().filter(itemDetail ->
          itemDetail.getName().equals(request.getName())).findAny();
      if (item != null && item.isPresent()) {
        Long itemId = item.get().getId();
        categoryItem.setItemId(itemId);
      }


      Optional<Category> category = categoryList.stream().filter(cat ->
          cat.getName().equals(request.getCategory())).findAny();
      if (category != null && category.isPresent()) {
        Long categoryId = category.get().getId();
        categoryItem.setCategoryId(categoryId);
      }

      categoryItems.add(categoryItem);
    });

    categoryItemRepository.saveAll(categoryItems);

    return true;
  }

  @Override
  public Object getItems() {
    return itemRepository.findAll();
  }

  @Override
  public Object getCategories() {
    return categoryRepository.findAll();
  }
}
