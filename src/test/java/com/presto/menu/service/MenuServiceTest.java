package com.presto.menu.service;

import com.presto.menu.repository.CategoryRepository;
import com.presto.menu.repository.ItemRepository;
import com.presto.menu.repository.entity.Category;
import com.presto.menu.repository.entity.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Profile("test")
public class MenuServiceTest {

  private ItemRepository itemRepository;
  private CategoryRepository categoryRepository;

  @Before
  public void setUpMock() {
    itemRepository = Mockito.mock(ItemRepository.class);
    categoryRepository = Mockito.mock(CategoryRepository.class);
  }

  @Test
  public void testMockCreation() {
    Assert.assertNotNull(itemRepository);
    Assert.assertNotNull(categoryRepository);
  }

  @Test
  public void testGetItem() {
    List<Item> items = new ArrayList<>();

    Item item = new Item();
    item.setId(1l);
    item.setName("French Fries");
    items.add(item);

    item = new Item();
    item.setId(2l);
    item.setName("Onion Rings");
    items.add(item);

    Mockito.when(itemRepository.findAll()).thenReturn(items);
    Assert.assertEquals("French Fries",
        itemRepository.findAll().get(0).getName());
    Assert.assertEquals("Onion Rings",
        itemRepository.findAll().get(1).getName());
  }

  @Test
  public void testGetCategory() {
    List<Category> categories = new ArrayList<>();

    Category category = new Category();
    category.setId(1l);
    category.setName("Appetizer");
    categories.add(category);

    category = new Category();
    category.setId(2l);
    category.setName("Entree");
    categories.add(category);

    Mockito.when(categoryRepository.findAll()).thenReturn(categories);
    Assert.assertEquals("Appetizer",
        categoryRepository.findAll().get(0).getName());
    Assert.assertEquals("Entree",
        categoryRepository.findAll().get(1).getName());
  }
}
