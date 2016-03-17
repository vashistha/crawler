package com.vk.crawler.cobol.batch.model;

import com.vk.crawler.core.model.DataModel;
import com.vk.crawler.core.util.StringUtils;


public class FileProperties implements DataModel {
  private String fileName;
  private String relativePath;
  private String digest;
  
  
  public FileProperties(String fileName, String absolutePath, String digest) {
    super();
    this.fileName = fileName;
    this.relativePath = absolutePath;
    this.digest = digest;
  }
  
  public String getFileName() {
    return fileName;
  }
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  
  public String getDigest() {
    return digest;
  }
  public void setDigest(String digest) {
    this.digest = digest;
  }
  
  @Override
  public boolean isEmpty() {
    return StringUtils.isEmpty(fileName);
  }

  public String getRelativePath() {
    return relativePath;
  }

  public void setRelativePath(String relativePath) {
    this.relativePath = relativePath;
  }
}
