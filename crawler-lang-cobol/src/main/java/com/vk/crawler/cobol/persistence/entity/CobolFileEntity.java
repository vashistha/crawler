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

import com.vk.crawler.cobol.model.CobolFileProperties;
import com.vk.crawler.core.model.DataModel;
import com.vk.crawler.core.util.StringUtils;

@Entity
@Table(name="VI_COBOL_FILE")
@TableGenerator(name = "cobol_file", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "VI_COBOL_FILE", valueColumnName = "SEQ_COUNT", allocationSize = 1)
@NamedQueries({
  @NamedQuery(name="CobolFileEntity.findAll", query="SELECT p FROM CobolFileEntity p"),
  @NamedQuery(name="CobolFileEntity.findByTriggerId", query="SELECT p FROM CobolFileEntity p WHERE p.triggerId=:triggerId"),
  @NamedQuery(name="CobolFileEntity.deleteByTriggerId", query="DELETE FROM CobolFileEntity p WHERE p.triggerId=:triggerId")
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
  
  public static CobolFileEntity valueOf(CobolFileProperties model) {
    CobolFileEntity e = new CobolFileEntity();
    e.setId(model.getId());
    e.setFileName(model.getFileName());
    e.setRelativePath(model.getRelativePath());
    e.setDigest(model.getDigest());
    e.setTriggerId(model.getTriggerId());
    
    return e;
  }
  
  public CobolFileProperties toModel() {
    CobolFileProperties m = new CobolFileProperties();
    m.setId(this.id);
    m.setFileName(this.fileName);
    m.setRelativePath(this.relativePath);
    m.setDigest(this.digest);
    m.setTriggerId(this.triggerId);
    return m;
  }
}
