package com.bakaoh.common;

/**
 *
 * @author taitt
 */
public class ServiceInfo {

    private static String FORMAT = "{\"port\":%d,\"host\":\"%s\",\"version\":\"%s\"}";
    public String version;
    public String host;
    public int port;

    public ServiceInfo(String host, int port, String version) {
        this.host = host;
        this.port = port;
        this.version = version;
    }

    public String getString() {
        return String.format(FORMAT, port, host, version);
    }
}