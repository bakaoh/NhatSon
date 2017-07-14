package com.bakaoh.nhatson.repository;

import com.bakaoh.nhatson.domain.Article;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
}