package com.bakaoh.nhatson.repository;

import com.bakaoh.nhatson.domain.Job;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface JobRepository extends PagingAndSortingRepository<Job, Long> {
}