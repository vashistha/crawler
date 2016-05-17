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

package com.vk.crawler.plsql.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.vk.crawler.core.model.DataModel;
import com.vk.crawler.core.util.StringUtils;

@Entity
@Table(name="VI_DB_CRAWLER_COLUMNS")
@TableGenerator(name = "column_entity", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "VI_DB_CRAWLER_COLUMNS", valueColumnName = "SEQ_COUNT", allocationSize = 1)
@NamedQuery(name="DBCrawlerColumnEntity.findAll", query="SELECT d FROM DBCrawlerColumnEntity d")
public class DBCrawlerColumnEntity implements DataModel {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy=GenerationType.TABLE, generator="column_entity")
  private Integer id;

  private String name;
  
  @Column(name="TABLE_NAME")
  private String tableName;

  @Lob
  private String remarks;
  
  @Column(name="DATA_TYPE")
  private Integer dataType;
  
  @Column(name="TYPE_NAME")
  private String typeName;

  @Column(name="COLUMN_SIZE")
  private Integer columnSize;

  private String nullable;
  
  @Column(name="COLUMN_DEF")
  private String columnDef;

  @Column(name="ORDINAL_POSITION")
  private Integer ordinalPosition;
  
  @Column(name="CHAR_OCTET_LENGTH")
  private Integer charOctetLength;

  @Column(name="IS_AUTOINCREMENT")
  private String isAutoincrement;

  @Column(name="IS_GENERATEDCOLUMN")
  private String isGeneratedcolumn;

  @Column(name="TRIGGER_ID")
  private Integer triggerId;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getCharOctetLength() {
    return this.charOctetLength;
  }

  public void setCharOctetLength(Integer charOctetLength) {
    this.charOctetLength = charOctetLength;
  }

  public String getColumnDef() {
    return this.columnDef;
  }

  public void setColumnDef(String columnDef) {
    this.columnDef = columnDef;
  }

  public Integer getColumnSize() {
    return this.columnSize;
  }

  public void setColumnSize(Integer columnSize) {
    this.columnSize = columnSize;
  }

  public Integer getDataType() {
    return this.dataType;
  }

  public void setDataType(Integer dataType) {
    this.dataType = dataType;
  }

  public String getIsAutoincrement() {
    return this.isAutoincrement;
  }

  public void setIsAutoincrement(String isAutoincrement) {
    this.isAutoincrement = isAutoincrement;
  }

  public String getIsGeneratedcolumn() {
    return this.isGeneratedcolumn;
  }

  public void setIsGeneratedcolumn(String isGeneratedcolumn) {
    this.isGeneratedcolumn = isGeneratedcolumn;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNullable() {
    return this.nullable;
  }

  public void setNullable(String nullable) {
    this.nullable = nullable;
  }

  public Integer getOrdinalPosition() {
    return this.ordinalPosition;
  }

  public void setOrdinalPosition(Integer ordinalPosition) {
    this.ordinalPosition = ordinalPosition;
  }

  public String getRemarks() {
    return this.remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getTableName() {
    return this.tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public Integer getTriggerId() {
    return this.triggerId;
  }

  public void setTriggerId(Integer triggerId) {
    this.triggerId = triggerId;
  }

  public String getTypeName() {
    return this.typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public boolean isEmpty() {
    return StringUtils.isEmpty(name);
  }

  @Override
  public String toString() {
    return "<DBCrawlerColumnEntity><id>" + id + "</id><name>" + name + "</name><tableName>" + tableName
        + "</tableName><remarks>" + remarks + "</remarks><dataType>" + dataType + "</dataType><typeName>" + typeName
        + "</typeName><columnSize>" + columnSize + "</columnSize><nullable>" + nullable + "</nullable><columnDef>"
        + columnDef + "</columnDef><ordinalPosition>" + ordinalPosition + "</ordinalPosition><charOctetLength>"
        + charOctetLength + "</charOctetLength><isAutoincrement>" + isAutoincrement
        + "</isAutoincrement><isGeneratedcolumn>" + isGeneratedcolumn + "</isGeneratedcolumn><triggerId>" + triggerId
        + "</triggerId></DBCrawlerColumnEntity>";
  }
}