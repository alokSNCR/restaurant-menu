package com.presto.menu.service.impl;

import com.presto.menu.model.JoeMenuRequest;
import com.presto.menu.model.response.CategoryResponse;
import com.presto.menu.model.response.ItemResponse;
import com.presto.menu.model.response.Response;
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
  public Response addMenu(JoeMenuRequest menuRequest) {
    Response response = new Response();
    // clean the table before add menu everyday
    categoryItemRepository.deleteAll();
    categoryRepository.deleteAll();
    itemRepository.deleteAll();

    List<com.presto.menu.model.Category> requestCat = menuRequest.getCategory();
    List<Category> catList = new ArrayList<>();
    if (requestCat != null && !requestCat.isEmpty()) {
      requestCat.stream().forEach(cat -> {
        Category category = new Category();
        category.setName(cat.getName());
        catList.add(category);
      });
      List<Category> cateList = categoryRepository.saveAll(catList);
      response.setCategories(cateList);
    }

    List<com.presto.menu.model.Item> requestItem = menuRequest.getItem();
    List<Item> itemList = new ArrayList<>();
    if (requestItem != null && !requestItem.isEmpty()) {
      requestItem.stream().forEach(item -> {
        Item it = new Item();
        it.setName(item.getName());
        itemList.add(it);
      });
      List<Item> list = itemRepository.saveAll(itemList);
      response.setItems(list);
    }

    // save category item table
    if (requestItem != null && !requestItem.isEmpty()) {
      List<CategoryItems> listCatItem = new ArrayList<>();
      requestItem.stream().forEach(it -> {
        CategoryItems categoryItem = new CategoryItems();
        Item item = itemRepository.findItemByName(it.getName());
        categoryItem.setItem(item);
        Category cat = categoryRepository.findCategoryByName(it.getCategory());
        categoryItem.setCategory(cat);
        listCatItem.add(categoryItem);
      });
      categoryItemRepository.saveAll(listCatItem);
    }
    return response;
  }

  @Override
  public List<ItemResponse> getItems(JoeMenuRequest menuRequest) {
    List<ItemResponse> itemResponses = new ArrayList<>();

    List<CategoryItems> listCatItem = categoryItemRepository.findAll();
    if (!listCatItem.isEmpty()) {
      listCatItem.stream().forEach(categoryItems -> {
        ItemResponse response = new ItemResponse();
        if (categoryItems.getItem() != null) {
          Optional<Item> item
              = itemRepository.findById(categoryItems.getItem().getId());
          if (item.isPresent()) {
            response.setName(item.get().getName());
          }
        }

        if (categoryItems.getCategory() != null) {
          Optional<Category> category
              = categoryRepository.findById(categoryItems.getCategory().getId());
          if (category.isPresent()) {
            response.setCategoryName(category.get().getName());
          }
        }
        itemResponses.add(response);
      });
    }
    return itemResponses;
  }

  @Override
  public List<CategoryResponse> getCategories(JoeMenuRequest menuRequest) {
    List<CategoryResponse> catList = new ArrayList<>();
    List<Category> categories = categoryRepository.findAll();
    if (!categories.isEmpty()) {
      categories.stream().forEach(category -> {
        CategoryResponse response = new CategoryResponse();
        response.setName(category.getName());
        catList.add(response);
      });
    }
    return catList;
  }
}
