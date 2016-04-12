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

package com.vk.example.entity;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import com.vk.crawler.cobol.persistence.dao.CobolFileDao;
import com.vk.crawler.cobol.persistence.entity.CobolFileEntity;

@ImportResource({"classpath:/cobol-spring-context.xml", 
                 "classpath:/persistence-context.xml"})

public class TestEntity {
  public static void main(String[] args) {

    SpringApplication app = new SpringApplication(TestEntity.class);
    app.setWebEnvironment(false);
    ConfigurableApplicationContext ctx= app.run(args);
    
    CobolFileDao cobolFileDao = (CobolFileDao) ctx.getBean("cobolFileDao");
    callEntity(cobolFileDao,"name1");
    callEntity(cobolFileDao,"name2");
    System.out.println("Done");

  }
  
  public static void callEntity(CobolFileDao cobolFileDao, String fileName) {
    try {
      CobolFileEntity entity = new CobolFileEntity();
      entity.setFileName(fileName);
      entity.setDigest("D");
      entity.setRelativePath("Path");
      entity.setTriggerId(1);
      cobolFileDao.create(entity);
    } 
    catch (Exception e) {
      e.printStackTrace();
    }

  }
}
