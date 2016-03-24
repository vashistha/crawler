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

import com.vk.crawler.cobol.model.CobolFileCodeMetrics;
import com.vk.crawler.core.model.DataModel;
import com.vk.crawler.core.util.NumberUtils;

@Entity
@Table(name="VI_COBOL_FILE_CODE_METRICS")
@TableGenerator(name = "cobol_code_metrics", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "VI_COBOL_FILE_CODE_METRICS", valueColumnName = "SEQ_COUNT", allocationSize = 1)
@NamedQueries({
  @NamedQuery(name="CobolFileCodeMetricsEntity.findAll", query="SELECT p FROM CobolFileCodeMetricsEntity p")
})
public class CobolFileCodeMetricsEntity implements DataModel {

  @Id
  @GeneratedValue(strategy=GenerationType.TABLE, generator = "cobol_code_metrics")
  private Integer id;

  @Column(name = "CF_ID")
  private Integer cobolFileId;
  
  @Column(name = "LOC_ACTIVE")
  private Integer locActive;
  
  @Column(name = "LOC_COMMENT")
  private Integer locComment;
  
  private String description;
  
  @Column(name = "TRIGGER_ID")
  private Integer triggerId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getCobolFileId() {
    return cobolFileId;
  }

  public void setCobolFileId(Integer cobolFileId) {
    this.cobolFileId = cobolFileId;
  }

  public Integer getLocActive() {
    return locActive;
  }

  public void setLocActive(Integer locActive) {
    this.locActive = locActive;
  }

  public Integer getLocComment() {
    return locComment;
  }

  public void setLocComment(Integer locComment) {
    this.locComment = locComment;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getTriggerId() {
    return triggerId;
  }

  public void setTriggerId(Integer triggerId) {
    this.triggerId = triggerId;
  }

  public boolean isEmpty() {
    return NumberUtils.isEmpty(cobolFileId);
  }

  public static CobolFileCodeMetricsEntity valueOf(CobolFileCodeMetrics model) {
    CobolFileCodeMetricsEntity a = new CobolFileCodeMetricsEntity();
    a.setCobolFileId(model.getCobolFileId());
    a.setDescription(model.getDescription());
    a.setId(model.getId());
    a.setLocActive(model.getLocActive());
    a.setLocComment(model.getLocComment());
    a.setTriggerId(model.getTriggerId());
    
    return a;
  }
  
  public CobolFileCodeMetrics toModel() {
    
    CobolFileCodeMetrics a = new CobolFileCodeMetrics();
    a.setCobolFileId(cobolFileId);
    a.setDescription(description);
    a.setId(id);
    a.setLocActive(locActive);
    a.setLocComment(locComment);
    a.setTriggerId(triggerId);
    
    return a;
  }
  
  public String toString() {
    return "<CobolFileCodeMetricsEntity><id>" + id + "</id><cobolFileId>"
        + cobolFileId + "</cobolFileId><locActive>" + locActive
        + "</locActive><locComment>" + locComment
        + "</locComment><description>" + description
        + "</description><triggerId>" + triggerId
        + "</triggerId></CobolFileCodeMetricsEntity>";
  }
}
