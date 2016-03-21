package com.vk.entity;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.vk.crawler.cobol.persistence.dao.CobolFileDao;
import com.vk.crawler.cobol.persistence.entity.CobolFileEntity;

@ImportResource({"classpath:/spring-context.xml", 
                 "classpath:/persistence-context.xml"})
@Configuration
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
