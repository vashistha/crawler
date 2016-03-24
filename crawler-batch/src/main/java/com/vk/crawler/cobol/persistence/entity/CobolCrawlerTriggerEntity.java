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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.vk.crawler.cobol.batch.model.CobolCrawlerTrigger;
import com.vk.crawler.core.model.DataModel;
import com.vk.crawler.core.util.StringUtils;

@Entity
@Table(name="VI_COBOL_CRAWLER_TRIGGER")
@TableGenerator(name = "cobol_crawler_trigger", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "VI_COBOL_CRAWLER_TRIGGER", valueColumnName = "SEQ_COUNT", allocationSize = 1)
@NamedQueries({
  @NamedQuery(name="CobolCrawlerTriggerEntity.findAll", query="SELECT p FROM CobolCrawlerTriggerEntity p"),
  @NamedQuery(name="CobolCrawlerTriggerEntity.findByTriggerName", query="SELECT p FROM CobolCrawlerTriggerEntity p WHERE UPPER(p.triggerName) LIKE :triggerName")
})
public class CobolCrawlerTriggerEntity implements DataModel {

  private static final long serialVersionUID = 2719950390467995957L;

  @Id
  @GeneratedValue(strategy=GenerationType.TABLE, generator = "cobol_crawler_trigger")
  private Integer id;

  @Column(name="TRIGGER_NAME")
  private String triggerName;
  
  private String description;

  private String path;
  
  private String status;
  
  @Column(name="PROCESSING_TIME")
  private Integer processingTime;
  
  @Column(name="CREATED_BY")
  private String createdBy;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="CREATED_TS")
  private Date createdTs;

  @Column(name="UPDATED_BY")
  private String updatedBy;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="UPDATED_TS")
  private Date updatedTs;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTriggerName() {
    return triggerName;
  }

  public void setTriggerName(String triggerName) {
    this.triggerName = triggerName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getProcessingTime() {
    return processingTime;
  }

  public void setProcessingTime(Integer processingTime) {
    this.processingTime = processingTime;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedTs() {
    return createdTs;
  }

  public void setCreatedTs(Date createdTs) {
    this.createdTs = createdTs;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Date getUpdatedTs() {
    return updatedTs;
  }

  public void setUpdatedTs(Date updatedTs) {
    this.updatedTs = updatedTs;
  }
  
  public CobolCrawlerTrigger toModel() {
    CobolCrawlerTrigger a = new CobolCrawlerTrigger();
    a.setId(id);
    a.setTriggerName(triggerName);
    a.setDescription(description);
    a.setPath(path);
    a.setStatus(status);
    a.setProcessingTime(processingTime);
    a.setCreatedBy(createdBy);
    a.setCreatedTs(createdTs);
    a.setUpdatedBy(updatedBy);
    a.setUpdatedTs(updatedTs);
    return a;
  }
  
  public static CobolCrawlerTriggerEntity valueOf(CobolCrawlerTrigger model) {
    CobolCrawlerTriggerEntity a = new CobolCrawlerTriggerEntity();
    a.setId(model.getId());
    a.setTriggerName(model.getTriggerName());
    a.setDescription(model.getDescription());
    a.setPath(model.getPath());
    a.setStatus(model.getStatus());
    a.setProcessingTime(model.getProcessingTime());
    a.setCreatedBy(model.getCreatedBy());
    a.setCreatedTs(model.getCreatedTs());
    a.setUpdatedBy(model.getUpdatedBy());
    a.setUpdatedTs(model.getUpdatedTs());
    return a;
  }
  
  public boolean isEmpty() {
    return StringUtils.isEmpty(triggerName);
  }
}