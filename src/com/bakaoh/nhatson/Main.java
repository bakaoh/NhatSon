package com.bakaoh.nhatson;

import com.bakaoh.common.JettyBootstrap;
import com.bakaoh.common.ServiceInfo;
import com.vng.zing.lib.Config;
import com.vng.zing.lib.LogUtil;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 *
 * @author tiepvv
 */
public class Main {

    private static final String API_BASE_PATH = Config.getParamStr("setting", "basepath");
    public static final String SERVICE_VERSION = "0.0.1";

    public static void main(String[] args) throws InterruptedException {
        ServiceInfo serviceInfo = new ServiceInfo(
                System.getProperty("apphost"),
                Integer.parseInt(System.getProperty("appport")),
                SERVICE_VERSION);

        LogUtil.init("service");
        JettyBootstrap.newInstance(apiHandler()).bind(serviceInfo.port);
    }

    private static Handler apiHandler() {

        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(StatsController.class, API_BASE_PATH + "/stats");
        
        ResourceHandler resourse_handle = new ResourceHandler();
        resourse_handle.setDirectoriesListed(false);
        resourse_handle.setResourceBase("public");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourse_handle, handler});
        return handlers;
    }
}
