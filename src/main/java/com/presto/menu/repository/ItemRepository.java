package com.presto.menu.repository;

import com.presto.menu.repository.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

  @Query(value = "SELECT f.ITEM_ID FROM ITEMS f where f.name = :name", nativeQuery = true)
  Long findIdByName(@Param("name") String name);

  @Query(value = "SELECT * FROM ITEMS f where f.name = :name", nativeQuery = true)
  Item findItemByName(@Param("name") String name);

}
