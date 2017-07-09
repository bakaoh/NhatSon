package com.bakaoh.common;

import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlets.gzip.GzipHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

/**
 *
 * @author taitt
 */
public class JettyBootstrap
{

    private static final Logger logger_ = Logger.getLogger(JettyBootstrap.class);
    private final Server server;
    private final AtomicBoolean isRunning;

    public static JettyBootstrap newInstance(Handler handler) {
        return new JettyBootstrap(handler);
    }

    public JettyBootstrap(Handler handler) {

        // init pool and server
        QueuedThreadPool threadPool = new QueuedThreadPool(1000, 100);
        server = new Server(threadPool);

        // create handler
        server.setHandler(handler);
        GzipHandler gzipHandler = new GzipHandler();
        gzipHandler.setMimeTypes("text/xml,text/html,text/plain,image/jpg,image/png");
        gzipHandler.setHandler(handler);
        server.setHandler(gzipHandler);

        isRunning = new AtomicBoolean(false);
    }

    public void bind(int port) {
        if (isRunning.compareAndSet(false, true)) {

            try {
                // init nio selector
                HttpConfiguration http_config = new HttpConfiguration();
                http_config.setRequestHeaderSize(10240);
                http_config.setResponseHeaderSize(10240);
                http_config.setOutputBufferSize(32896);
                HttpConnectionFactory http_factory = new HttpConnectionFactory(http_config);

                ServerConnector connector = new ServerConnector(server, http_factory);
                connector.setPort(port);
                connector.setAcceptQueueSize(10);
                connector.setIdleTimeout(2000);
                server.setConnectors(new Connector[]{connector});

                // start server
                server.start();
                System.out.println("Successful start aspired. Server port: " + port);

            } catch (Exception ex) {
                logger_.error("bind error: ", ex);
            }
        } else {
            logger_.error("Server is running");
        }
    }

    public void stop() {
        try {
            server.stop();
            isRunning.set(false);
        } catch (Exception ex) {
            logger_.error("stop error: ", ex);
        }
    }
}
