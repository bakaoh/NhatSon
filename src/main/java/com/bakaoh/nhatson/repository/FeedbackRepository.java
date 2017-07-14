package com.bakaoh.nhatson.repository;

import com.bakaoh.nhatson.domain.Feedback;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface FeedbackRepository extends PagingAndSortingRepository<Feedback, Long> {
}