package com.presto.menu.repository;

import com.presto.menu.repository.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  @Query(value = "SELECT f.CATEGORY_ID FROM CATEGORIES f where f.name = :name", nativeQuery = true)
  Long findIdByCategoryName(@Param("name") String name);

  @Query(value = "SELECT * FROM CATEGORIES f where f.name = :name", nativeQuery = true)
  Category findCategoryByName(@Param("name") String name);


}
