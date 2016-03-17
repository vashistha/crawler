package com.vk.crawler.cobol.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.vk.crawler.core.model.DataModel;
import com.vk.crawler.core.util.StringUtils;

@Entity
@Table(name="VI_COBOL_FILE")
@TableGenerator(name = "cobol_file", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "VI_COBOL_FILE", valueColumnName = "SEQ_COUNT", allocationSize = 1)
@NamedQueries({
  @NamedQuery(name="CobolFileEntity.findAll", query="SELECT p FROM CobolFileEntity p")
})
public class CobolFileEntity implements DataModel {

  @Id
  @GeneratedValue(strategy=GenerationType.TABLE, generator = "cobol_file")
  private Integer id;
  
  @Column(name = "NAME")
  private String fileName;
  
  @Column(name = "PATH")
  private String relativePath;
  
  private String digest;
  
  @Column(name = "TRIGGER_ID")
  private Integer triggerId;
  
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getFileName() {
    return fileName;
  }
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  public String getRelativePath() {
    return relativePath;
  }
  public void setRelativePath(String relativePath) {
    this.relativePath = relativePath;
  }
  public String getDigest() {
    return digest;
  }
  public void setDigest(String digest) {
    this.digest = digest;
  }
  public Integer getTriggerId() {
    return triggerId;
  }
  public void setTriggerId(Integer triggerId) {
    this.triggerId = triggerId;
  }
  @Override
  public boolean isEmpty() {
    return StringUtils.isEmpty(fileName);
  }
  
}
