package com.presto.menu.repository;

import com.presto.menu.repository.entity.CategoryItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryItemRepository extends JpaRepository<CategoryItems, Long> {
}
