package com.bakaoh.nhatson.controller;

import com.bakaoh.nhatson.domain.Feedback;
import com.bakaoh.nhatson.model.ArticleModel;
import com.bakaoh.nhatson.model.CommonModel;
import com.bakaoh.nhatson.model.JobModel;
import com.bakaoh.nhatson.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ApplicationController {

    @Autowired
    private CommonModel common;

    @Autowired
    private ArticleModel article;

    @Autowired
    private JobModel job;

    @Autowired
    private FeedbackRepository feedback;

    @RequestMapping("/")
    public String home(Model model) {
        common.addCommonUrl(model);
        common.addMenu(model);
        common.showBanner(model);
        article.addHomeTabs(model);
        article.addRecents(model, 3);
        return "index";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model) {
        common.addCommonUrl(model);
        common.addMenu(model);
        return "lienhe";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String contactSubmit(Model model, HttpServletRequest request) {
        Feedback data = new Feedback();
        data.setName(request.getParameter("name"));
        data.setPhone(request.getParameter("phone"));
        data.setEmail(request.getParameter("email"));
        data.setSubject(request.getParameter("subject"));
        data.setMessage(request.getParameter("message"));
        feedback.save(data);
        return "lienhe";
    }

    @RequestMapping("/job")
    public String job(Model model) {
        common.addCommonUrl(model);
        common.addMenu(model);
        job.addList(model);
        return "tuyendung";
    }

    @RequestMapping("/job/{job_id}")
    public String job(Model model, @PathVariable("job_id") String id) {
        common.addCommonUrl(model);
        common.addMenu(model);
        job.addDetail(model, Long.valueOf(id));
        return "tuyendung_detail";
    }

    @RequestMapping("/detail/{article_id}")
    public String detail(Model model, @PathVariable("article_id") String id) {
        common.addCommonUrl(model);
        common.addMenu(model);
        article.addDetail(model, Long.valueOf(id));
        article.addFeatures(model);
        article.addRecents(model, 5);
        return "detail";
    }

    @RequestMapping("/info/{category_id}")
    public String info(Model model, @PathVariable("category_id") String id) {
        common.addCommonUrl(model);
        common.addMenu(model);
        article.addNewest(model, Long.valueOf(id));
        article.addFeatures(model);
        article.addRecents(model, 5);
        return "detail";
    }

    @RequestMapping("/news/{category_id}")
    public String news(Model model, @PathVariable("category_id") String id) {
        common.addCommonUrl(model);
        common.addMenu(model);
        article.addList(model, Long.valueOf(id));
        article.addFeatures(model);
        article.addRecents(model, 5);
        return "tintuc";
    }
}