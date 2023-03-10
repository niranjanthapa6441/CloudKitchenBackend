package com.example.CloudKitchenBackend.Repositories;

import com.example.CloudKitchenBackend.Model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {
    Page<Category> findByCategory(String category, Pageable paging);
    Optional<Category> findByCategory(String foodType);
}
