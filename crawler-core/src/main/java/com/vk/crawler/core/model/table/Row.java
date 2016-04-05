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
