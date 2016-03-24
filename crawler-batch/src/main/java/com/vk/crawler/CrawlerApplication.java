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

package com.vk.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath:/jobs/cobol-crawler-batch.xml",
  "classpath:/persistence-context.xml",
  "classpath:/spring-context.xml"})

public class CrawlerApplication {

  private static final Logger logger = LoggerFactory.getLogger(CrawlerApplication.class);
  
  public static void main(String[] args) {
    //ConfigurableApplicationContext ctx= SpringApplication.run(CrawlerApplication.class, args);
    SpringApplication app = new SpringApplication(CrawlerApplication.class);
    app.setWebEnvironment(false);
    ConfigurableApplicationContext ctx= app.run(args);
    CrawlerApplication obj = new CrawlerApplication();
   
    obj.runCobolCrawler(ctx);
   }
  
  public void runCobolCrawler(ConfigurableApplicationContext ctx) {
    try {
      InvokeCobolCrawler cobolCrawler = (InvokeCobolCrawler) ctx.getBean("cobolCrawler");
      cobolCrawler.start(1);
      logger.debug("Cobol Crawler completed successfully.");
    }
    catch (Exception e) {
      logger.error("EXCEPTION");
      logger.error("", e);
    }
  }
}
