package com.bakaoh.nhatson.model;

import com.bakaoh.nhatson.domain.Article;
import com.bakaoh.nhatson.domain.Job;
import com.bakaoh.nhatson.repository.JobRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.*;

/**
 * Created by taitt on 11/07/2017.
 */
@Component
public class JobModel {

    @Autowired
    private JobRepository job;

    private Map<Long, Job> getAll() {
        Map<Long, Job> rs = new HashMap<>();
        Iterator<Job> iterator = job.findAll().iterator();
        while (iterator.hasNext()) {
            Job job = iterator.next();
            rs.put(job.getId(), job);
        }
        return rs;
    }

    public void addList(Model model) {
        List<Job> jobs = Lists.newArrayList(getAll().values());
        model.addAttribute("jobs", jobs);
    }

    public void addDetail(Model model, Long jobId) {
        model.addAttribute("job", getAll().get(jobId));
    }
}
