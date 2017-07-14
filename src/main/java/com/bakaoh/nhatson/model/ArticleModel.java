package com.bakaoh.nhatson.model;

import com.bakaoh.nhatson.domain.Article;
import com.bakaoh.nhatson.domain.Category;
import com.bakaoh.nhatson.repository.ArticleRepository;
import com.bakaoh.nhatson.repository.CategoryRepository;
import com.bakaoh.nhatson.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by taitt on 11/07/2017.
 */
@Component
public class ArticleModel {

    @Autowired
    private CategoryRepository category;

    @Autowired
    private ArticleRepository article;

    private synchronized List<ArticleData> getAllArticle() {
        if (articleCache == null || System.currentTimeMillis() - articleLastUpdate > 10000) {
            articleCache = new ArrayList<>();
            Iterator<Article> iterator = article.findAll().iterator();
            while (iterator.hasNext()) {
                Article article = iterator.next();
                ArticleData data = new ArticleData();
                data.id = article.getId();
                data.category = article.getCategory().getId();
                data.title = article.getTitle();
                data.detail = article.getDetail();
                data.brief = article.getDetail().length() > 200
                        ? article.getDetail().substring(0, 200) : article.getDetail();
                data.image = Utils.getImageUrl(article.getImage());
                data.url = "/detail/" + article.getId();
                data.time = Utils.format(article.getCreationDate());
                data.feature = article.isFeature();
                articleCache.add(data);
            }
            articleLastUpdate = System.currentTimeMillis();
        }
        return articleCache;
    }

    private List<ArticleData> articleCache;
    private long articleLastUpdate;

    public void addDetail(Model model, long articleId) {
        ArticleData article = null;
        List<ArticleData> relateds = new ArrayList<>();
        List<ArticleData> all = getAllArticle();
        for (ArticleData a : all) {
            if (a.id == articleId) {
                article = a;
            } else if (article != null && a.category == article.category) {
                relateds.add(a);
                if (relateds.size() >= 4) break;
            }
        }

        model.addAttribute("article", article);
        model.addAttribute("relateds", relateds.isEmpty() ? null : relateds);
    }

    public void addList(Model model, long categoryId) {
        List<ArticleData> list = new ArrayList<>();
        List<ArticleData> all = getAllArticle();
        for (ArticleData a : all) {
            if (a.category == categoryId) {
                list.add(a);
            }
        }
        model.addAttribute("list", list);
    }

    public void addFeatures(Model model) {
        List<ArticleData> features = new ArrayList<>();
        List<ArticleData> all = getAllArticle();
        for (ArticleData a : all) {
            if (a.feature) {
                features.add(a);
                if (features.size() >= 6) break;
            }
        }
        model.addAttribute("features", features);
    }

    public void addRecents(Model model, int limit) {
        List<ArticleData> recents = new ArrayList<>(1);
        List<ArticleData> all = getAllArticle();
        for (ArticleData a : all) {
            recents.add(a);
            if (recents.size() >= limit) break;
        }
        model.addAttribute("recents", recents.isEmpty() ? null : recents);
    }

    public void addNewest(Model model, long categoryId) {
        ArticleData article = null;
        List<ArticleData> relateds = new ArrayList<>();
        List<ArticleData> all = getAllArticle();
        for (ArticleData a : all) {
            if (a.category == categoryId) {

                if (article == null) {
                    article = a;
                } else {
                    relateds.add(a);
                    if (relateds.size() >= 4) break;
                }
            }
        }

        model.addAttribute("article", article);
        model.addAttribute("relateds", relateds.isEmpty() ? null : relateds);
    }

    public synchronized void addHomeTabs(Model model) {
        if (tabsCache == null || System.currentTimeMillis() - tabsLastUpdate > 60000) {
            tabsCache = new ArrayList<>();
            Iterator<Category> iterator = category.findAll().iterator();
            List<ArticleData> all = getAllArticle();
            int count = 0;
            while (iterator.hasNext() && count < 5) {
                Category cate = iterator.next();
                if (cate.isHome()) {
                    TabData tab = null;
                    for (ArticleData a : all) {
                        if (a.category == cate.getId()) {
                            if (tab == null) {
                                tab = new TabData();
                                count++;
                                tab.id = count;
                                tab.name = cate.getName();
                                tab.article = a;
                            } else {
                                tab.relates.add(a);
                                if (tab.relates.size() >= 6) break;
                            }
                        }
                    }
                    if (tab != null) tabsCache.add(tab);
                }
            }
            tabsLastUpdate = System.currentTimeMillis();
        }

        model.addAttribute("tabs", tabsCache.isEmpty() ? null : tabsCache);
    }

    private List<TabData> tabsCache;
    private long tabsLastUpdate;

    static class TabData {
        public int id;
        public String name;
        public ArticleData article;
        public List<ArticleData> relates = new ArrayList<>();
    }

    static class ArticleData {
        public long id;
        public String url;
        public String image;
        public String title;
        public String time;
        public String brief;
        public String detail;
        public long category;
        public boolean feature;
    }
}
