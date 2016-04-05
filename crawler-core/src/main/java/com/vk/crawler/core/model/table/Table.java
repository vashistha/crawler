package com.vk.crawler.core.model.table;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="table")
@XmlAccessorType(XmlAccessType.FIELD)
public class Table {
  
  @XmlAttribute(name="name")
  private String tableName;
  
  @XmlElement(name = "row", type = Row.class)
  private List<Row> rows;

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public List<Row> getRows() {
    return rows;
  }

  public void setRows(List<Row> rows) {
    this.rows = rows;
  }
  
  public void addRow(Row row) {
    if(rows == null) {
      rows = new ArrayList<>();
    }
    rows.add(row);
  }

  @Override
  public String toString() {
    return "<Table><tableName>" + tableName + "</tableName><rows>" + rows + "</rows></Table>";
  }
}
