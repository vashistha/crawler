/*
 * Copyright (C) 2016 by Vashistha kumar
 *
 *    Permission is hereby granted, free of charge, to any person obtaining a copy
 *    of this software and associated documentation files (the "Software"), to deal
 *    in the Software without restriction, including without limitation the rights
 *    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *    copies of the Software, and to permit persons to whom the Software is
 *    furnished to do so, subject to the following conditions:
 *
 *    The above copyright notice and this permission notice shall be included in
 *    all copies or substantial portions of the Software.
 *
 *    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *    THE SOFTWARE.
 */

package com.vk.crawler.web;

import java.util.HashMap;
import java.util.Map;

import javax.faces.webapp.FacesServlet;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.vk.crawler.web.jsf.utils.ViewScope;

/**
 * Created by Vashistha Kumar on 27/03/16.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.vk.crawler"})
public class CrawlerWebApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(CrawlerWebApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(CrawlerWebApplication.class, CrawlerWebInitializer.class);
  }
  
  @Bean
  public ServletRegistrationBean servletRegistrationBean() {
    FacesServlet servlet = new FacesServlet();
    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "*.jsf", "*.xhtml");
    return servletRegistrationBean;
  }
  
  @Bean
  public static CustomScopeConfigurer customScopeConfigurer() {
    CustomScopeConfigurer configurer = new CustomScopeConfigurer();
    Map<String, Object> scopes = new HashMap<String, Object>();
    scopes.put("view", new ViewScope());
    configurer.setScopes(scopes);
    return configurer;
  }
}
