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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.vk.crawler.core.util.StringUtils;

@XmlRootElement(name="tablelist")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TableMaps {

  private Map<String, Table> tableMap;
  private List<Table> tables;

  @XmlElement(name = "table", type = Table.class)
  public List<Table> getTables() {
    return tables;
  }

  public void setTables(List<Table> tables) {
    this.tables = tables;
  }

  public void addTable(Table table) {
    if(table != null && !StringUtils.isEmpty(table.getTableName())) {
      if(tableMap == null) {
        tableMap = new HashMap<>();
      }
      this.tableMap.put(table.getTableName(), table);
    }
  }

  public Table getTable(String tableName) {
    if(tableMap == null) {
      tableMap = new HashMap<>();
      for (Table table : tables) {
        addTable(table);
      }
    }
    return tableMap.get(tableName);
  }

  @Override
  public String toString() {
    return "<TableMaps><tableMap>" + tableMap + "</tableMap><tables>" + tables + "</tables></TableMaps>";
  }  
}
