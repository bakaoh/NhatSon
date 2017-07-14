package com.bakaoh.nhatson.repository;

import com.bakaoh.nhatson.domain.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
}