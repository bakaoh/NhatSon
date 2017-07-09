package com.bakaoh.common;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author taitt
 */
public class ApiResult {

    private Map<String, Object> rs;
    private static Gson gson = new Gson();

    public static ApiResult SUCCESS() {
        return new ApiResult(0, "Successful");
    }
    
    public static ApiResult BASE_INVALID_AUTH_KEY() {
        return new ApiResult(-701, "Invalid authen key");
    }
    
    public static ApiResult BASE_INVALID_ACTION() {
        return new ApiResult(-702, "Invalid action");
    }
    
    public static ApiResult BASE_INVALID_PARAMS() {
        return new ApiResult(-703, "Invalid params");
    }
    
    public static ApiResult BASE_SERVER_ERROR() {
        return new ApiResult(-704, "Server error");
    }
    
    public ApiResult(int err, String msg) {
        rs = new HashMap<>();
        rs.put("error_code", err);
        rs.put("error_msg", msg);
    }

    public Object get(String key) {
        return rs.get(key);
    }

    public ApiResult put(String key, Object value) {
        rs.put(key, value);
        return this;
    }

    public int error_code() {
        return (int) rs.get("error_code");
    }

    public ApiResult error_code(int err) {
        return put("error_code", err);
    }

    public String error_msg() {
        return (String) rs.get("error_msg");
    }

    public ApiResult error_msg(String msg) {
        return put("error_msg", msg);
    }

    public ApiResult data(Object msg) {
        return put("data", msg);
    }

    public String toJson() {
        return gson.toJson(rs);
    }
}
