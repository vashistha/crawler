package com.vk.crawler.cobol.batch;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;

import com.vk.crawler.cobol.batch.model.FileProperties;
import com.vk.crawler.cobol.persistence.entity.CobolFileEntity;

@StepScope
public class FileListProcessor implements ItemProcessor<FileProperties, CobolFileEntity> {

  private static final Logger logger = LoggerFactory.getLogger(FileListProcessor.class);
  
  @Value("#{jobParameters['rootDirectory']}")
  private String rootDirectory;
  public void setRootDirectory(final String name) {
    this.rootDirectory = name;
  }
  
  @Value("#{jobParameters['triggerId']}")
  private Integer triggerId;
  public void setTriggerId(Integer triggerId) {
    this.triggerId = triggerId;
  }
  
  @Override
  public CobolFileEntity process(FileProperties file) throws Exception {
    CobolFileEntity entity = new CobolFileEntity();
    
    entity.setFileName(file.getFileName());
    entity.setRelativePath(file.getRelativePath());
    entity.setTriggerId(triggerId);
    try {
      DigestUtils.md5Digest(new FileInputStream(rootDirectory + File.separator + file.getRelativePath() + File.separator + file.getFileName()));
    }
    catch (IOException e) {
      logger.error("Exception in preparing digest", e);
    }
    return entity;
  }
  
}
