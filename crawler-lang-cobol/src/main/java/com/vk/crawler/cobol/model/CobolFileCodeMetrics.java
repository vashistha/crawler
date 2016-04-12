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

package com.vk.crawler.cobol.model;

import com.vk.crawler.core.model.DataModel;
import com.vk.crawler.core.util.NumberUtils;

public class CobolFileCodeMetrics implements DataModel {
  private Integer id;
  private Integer cobolFileId;
  private Integer locActive;
  private Integer locComment;
  private Integer locOther;
  private StringBuffer description;
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
    return String.valueOf(description);
  }

  public void setDescription(String txt) {
    this.description = txt == null ? null : new StringBuffer(txt);
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
  

  public Integer getLocOther() {
    return locOther;
  }

  public void setLocOther(Integer locOther) {
    this.locOther = locOther;
  }

  public String toString() {
    return "<CobolFileCodeMetrics><id>" + id + "</id><cobolFileId>" + cobolFileId + "</cobolFileId><locActive>"
        + locActive + "</locActive><locComment>" + locComment + "</locComment><locOther>" + locOther
        + "</locOther><description>" + description + "</description><triggerId>" + triggerId
        + "</triggerId></CobolFileCodeMetrics>";
  }

  public void increamentCommentedLoc() {
    this.locComment = (this.locComment == null) ? 1 : this.locComment + 1;
  }

  public void increamentActiveLoc() {
    this.locActive = (this.locActive == null) ? 1 : this.locActive + 1;
  }
  
  public void increamentOtherLoc() {
    this.locOther = (this.locOther == null) ? 1 : this.locOther + 1;
  }
  
  public void appendDescription(String txt) {
    if (txt == null) {
      return;
    }
    if(this.description == null) {
      this.description = new StringBuffer(txt);
    }
    else {
      this.description.append(txt);
    }
  }
}
