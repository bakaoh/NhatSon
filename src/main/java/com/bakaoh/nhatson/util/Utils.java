package com.bakaoh.nhatson.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by taitt on 11/07/2017.
 */
public class Utils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");

    public static String getImageUrl(String image) {
        return image.replaceFirst("/domain/article", "/admin/rest/articles").replaceAll("\\.bin", "");
    }

    public static String format(Date date) {
        return sdf.format(date);
    }
}
