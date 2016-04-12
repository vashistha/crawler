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
 */E.
 */

package com.vk.crawler.cobol.batch.model;

import java.sql.Timestamp;
import java.util.Date;

import com.vk.crawler.core.model.DataModel;
import com.vk.crawler.core.util.StringUtils;

public class CobolCrawlerTrigger implements DataModel {
  private static final long serialVersionUID = 892166799541735176L;
  private Integer id;
  private String triggerName;
  private String description;
  private String path;
  private String status;
  private Integer processingTime;
  private String createdBy;
  private Date createdTs;
  private String updatedBy;
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
  
  public boolean isEmpty() {
    return StringUtils.isEmpty(triggerName);
  }

  @Override
  public String toString() {
    return "<CobolCrawlerTrigger><id>" + id + "</id><triggerName>"
        + triggerName + "</triggerName><description>" + description
        + "</description><path>" + path + "</path><status>" + status
        + "</status><processingTime>" + processingTime
        + "</processingTime><createdBy>" + createdBy
        + "</createdBy><createdTs>" + createdTs + "</createdTs><updatedBy>"
        + updatedBy + "</updatedBy><updatedTs>" + updatedTs
        + "</updatedTs></CobolCrawlerTrigger>";
  }
}