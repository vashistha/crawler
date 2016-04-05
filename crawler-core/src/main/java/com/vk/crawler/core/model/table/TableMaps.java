package com.vk.crawler.core.model.table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.vk.crawler.core.util.StringUtils;

@XmlRootElement(name="tables")
public class TableMaps {

  private Map<String, Table> tableMap = new HashMap<>();
  
  @XmlElement(name = "table", type = Table.class)
  private List<Table> tables;

  

  public List<Table> getTables() {
    return tables;
  }

  public void setTables(List<Table> tables) {
    this.tables = tables;
  }

  public void addTables(Table table) {
    if(table != null && !StringUtils.isEmpty(table.getTableName())) {
      this.tableMap.put(table.getTableName(), table);
    }
  }

  public Table getTable(String tableName) {
    return tableMap.get(tableName);
  }  
}
