package com.bakaoh.nhatson;

import com.bakaoh.common.BaseServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author taitt
 */
public class StatsController extends BaseServlet {

    @Override
    protected void process(HttpServletRequest req, HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder()
                .append("Version: ").append(Main.SERVICE_VERSION)
                .append("<br/>")
                .append("Environment: ").append(System.getProperty("environment"))
                .append("<br/>")
                .append(stats.htmlStat());
        this.out(sb.toString(), req, resp, "html");
    }
}
