package com.vk.crawler.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
public class CrawlerWebInitializer implements ServletContextInitializer {
  private Logger logger = LoggerFactory.getLogger(CrawlerWebInitializer.class);
  
  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
      logger.debug("Servlet context init.......................");
      System.out.println("Servlet context init..");
      servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", "true");
      servletContext.setInitParameter("primefaces.THEME", "bootstrap");
      /** Add below line to work without web.xml, otherwise face exception -- Could not find backup for factory javax.faces.context.FacesContextFactory. */
      servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
      servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
      servletContext.setInitParameter("primefaces.FONT_AWESOME", Boolean.TRUE.toString());
      servletContext.setInitParameter("primefaces.UPLOADER", "auto");
  }
}
