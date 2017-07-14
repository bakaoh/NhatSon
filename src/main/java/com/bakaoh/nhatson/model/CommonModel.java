package com.bakaoh.nhatson.model;

import com.bakaoh.nhatson.domain.Category;
import com.bakaoh.nhatson.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.*;

/**
 * Created by taitt on 11/07/2017.
 */
@Component
public class CommonModel {

    @Autowired
    private CategoryRepository category;

    public void addCommonUrl(Model model) {
        model.addAttribute("homeUrl", "/");
        model.addAttribute("hireUrl", "/job");
        model.addAttribute("contactUrl", "/contact");

        model.addAttribute("facebook", "https://facebook.com");
        model.addAttribute("youtube", "https://youtube.com");
        model.addAttribute("google", "https://google.com");
        model.addAttribute("skype", "https://skype");
    }

    public void showBanner(Model model) {
        model.addAttribute("showBanner", true);
    }

    public synchronized void addMenu(Model model) {
        if (menuCache == null || System.currentTimeMillis() - menuLastUpdate > 60000) {
            menuCache = new ArrayList<>();
            Iterator<Category> all = category.findAll().iterator();
            Map<Long, MenuItem> categories = new HashMap<>();
            while (all.hasNext()) {
                Category next = all.next();
                MenuItem menuItem = new MenuItem(next.getId(), next.getName(), next.isNewest());
                if (next.getParent() > 0)
                    categories.get(next.getParent()).addSub(menuItem);
                else categories.put(next.getId(), menuItem);
            }

            for (long i = 1; i <= 6; i++) {
                menuCache.add(categories.get(i));
            }
            menuLastUpdate = System.currentTimeMillis();
        }
        model.addAttribute("menu", menuCache);
    }

    private List<MenuItem> menuCache;
    private long menuLastUpdate;

    static class MenuItem {
        public long id;
        public String name;
        public String url;
        public List<MenuItem> subs = new ArrayList<>();

        public MenuItem(long id, String name, boolean isNewest) {
            this.id = id;
            this.name = name;
            this.url = isNewest ? "/info/" + id  : "/news/" + id;
        }

        public void addSub(MenuItem item) {
            subs.add(item);
        }
    }
}
