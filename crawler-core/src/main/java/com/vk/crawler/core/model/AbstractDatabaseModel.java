/*
 * Copyright (C) 2015 by Vashistha kumar
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package com.vk.crawler.core.model;

import java.util.Date;

import com.vk.crawler.core.util.DateUtils;


public abstract class AbstractDatabaseModel implements DataModel {
  
  private static final long serialVersionUID = -8658030208133077595L;
  private Integer createdBy;
  private Date createdTs;
  private Integer updatedBy;
  private Date updatedTs;

  public Integer getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedTs() {
    return createdTs;
  }

  public void setCreatedTs(Date createdTs) {
    this.createdTs = createdTs;
  }

  public Integer getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Integer updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Date getUpdatedTs() {
    return updatedTs;
  }

  public void setUpdatedTs(Date updatedTs) {
    this.updatedTs = updatedTs;
  }
  
  public String getFormattedCreatedTs() {
    return DateUtils.getFormattedDate(DISPLAY_TIME_FORMATE, createdTs);
  }
  
  public String getFormattedUpdatedTs() {
    return DateUtils.getFormattedDate(DISPLAY_TIME_FORMATE, updatedTs);
  }
  
  public String getFormattedUpdatedCreatedTs() {
    return updatedTs !=null ? DateUtils.getFormattedDate(DISPLAY_TIME_FORMATE, updatedTs) :
      DateUtils.getFormattedDate(DISPLAY_TIME_FORMATE, createdTs);
  }

  public String formatDate(Date date, String format) {
    return date != null ? DateUtils.getFormattedDate(format, date) : "";
  }
  @Override
  public String toString() {
    return "<createdBy>" + createdBy + "</createdBy> <createdTs>" + createdTs + "</createdTs> <updatedBy>"
        + updatedBy + "</updatedBy> <updatedTs>" + updatedTs + "</updatedTs>";
  }
}
