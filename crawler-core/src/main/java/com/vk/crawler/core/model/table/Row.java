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

package com.vk.crawler.core.model.table;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="row")
@XmlAccessorType(XmlAccessType.FIELD)
public class Row {
  
  @XmlAttribute(name="id")
  private Integer rowId;
  
  
  @XmlAttribute(name="header")
  private boolean isHeader;
  
  @XmlAttribute(name="footer")
  private boolean isFooter;
  
  @XmlElement(name = "column", type = Column.class)
  private List<Column> columns;

  public Integer getRowId() {
    return rowId;
  }

  public void setRowId(Integer rowId) {
    this.rowId = rowId;
  }

  public boolean isHeader() {
    return isHeader;
  }

  public void setHeader(boolean isHeader) {
    this.isHeader = isHeader;
  }

  public boolean isFooter() {
    return isFooter;
  }

  public void setFooter(boolean isFooter) {
    this.isFooter = isFooter;
  }

  public List<Column> getColumns() {
    return columns;
  }

  public void setColumns(List<Column> columns) {
    this.columns = columns;
  }
  
  public void addColumn(Column column) {
    if (columns == null) {
      columns = new ArrayList<>();
    }
    columns.add(column);
  }

  @Override
  public String toString() {
    return "<Row><rowId>" + rowId + "</rowId><isHeader>" + isHeader + "</isHeader><isFooter>" + isFooter
        + "</isFooter><columns>" + columns + "</columns></Row>";
  }
}
