package com.bakaoh.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author taitt
 */
public abstract class BaseServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(BaseServlet.class);
    protected static TStat stats = new TStat();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long startTimer = System.currentTimeMillis();
        process(req, resp);
        this.logStat(startTimer, ".doGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long startTimer = System.currentTimeMillis();
        process(req, resp);
        this.logStat(startTimer, ".doPost");
    }

    protected abstract void process(HttpServletRequest req, HttpServletResponse resp);

    protected void logStat(long start, String action) {
        stats.exec(this.getClass().getSimpleName() + action, System.currentTimeMillis() - start);
    }

    protected String getStringParam(HttpServletRequest req, String parameter,
            String defaultValue) {
        String strValue = req.getParameter(parameter);
        if (strValue != null && !strValue.isEmpty()) {
            return strValue;
        }
        return defaultValue;
    }

    protected int getIntParam(HttpServletRequest req, String parameter,
            int defaultValue) {
        String strValue = req.getParameter(parameter);
        if (strValue != null && strValue.matches("[0-9]+")) {
            try {
                return Integer.parseInt(strValue);
            } catch (Exception ex) {
                logger.warn("Can not get param, take default:" + parameter);
            }
        }
        return defaultValue;
    }

    protected List<Integer> getListParam(HttpServletRequest req,
            String parameter,
            List<Integer> defaultValue) {
        String strValue = req.getParameter(parameter);
        if (strValue != null) {
            try {
                String[] split = strValue.split(",");
                List<Integer> ids = new ArrayList<>();
                for (String idStr : split) {
                    ids.add(Integer.parseInt(idStr.trim()));
                }

                return ids;
            } catch (Exception ex) {
                logger.warn("Can not get param, take default:" + parameter, ex);
            }
        }
        return defaultValue;
    }

    protected void out(String content, HttpServletRequest req,
            HttpServletResponse resp, String type) {
        try {
            resp.setCharacterEncoding("utf-8");
            if ("json".equalsIgnoreCase(type)) {
                resp.addHeader("Content-Type", "application/json; charset=utf-8");
            } else if ("plain".equalsIgnoreCase(type)) {
                resp.addHeader("Content-Type", "text/plain; charset=utf-8");
            } else {
                resp.addHeader("Content-Type", "text/html; charset=utf-8");
            }
            resp.addHeader("Access-Control-Allow-Origin", "*");

            String callback = req.getParameter("callback");
            if (callback != null && !"".equals(callback)) {
                content = String.format("%s(%s)", callback, content);
            }
            write(content, resp);

        } catch (Exception e) {
            logger.warn("Can not write response to Writer", e);
        }
    }

    private void write(String content, HttpServletResponse resp) throws IOException {
        PrintWriter os = resp.getWriter();
        os.write(content);
        os.close();
    }
}
