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
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.vk.crawler.core.model.DataModel;
import com.vk.crawler.core.util.StringUtils;


@Entity
@Table(name="VI_DB_CRAWLER_INDEX_INFO")
@TableGenerator(name = "indexinfo_entity", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "VI_DB_CRAWLER_INDEX_INFO", valueColumnName = "SEQ_COUNT", allocationSize = 1)
@NamedQuery(name="DBCrawlerIndexInfoEntity.findAll", query="SELECT d FROM DBCrawlerIndexInfoEntity d")
public class DBCrawlerIndexInfoEntity implements DataModel {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy=GenerationType.TABLE, generator="indexinfo_entity")
  private int id;

  @Column(name="INDEX_NAME")
  private String indexName;

  @Column(name="TABLE_NAME")
  private String tableName;

  @Column(name="COLUMN_NAME")
  private String columnName;

  @Column(name="ASC_OR_DESC")
  private String ascOrDesc;

  private int cardinality;

  @Column(name="FILTER_CONDITION")
  private String filterCondition;
  
  @Column(name="INDEX_QUALIFIER")
  private String indexQualifier;

  @Column(name="INDEX_TYPE")
  private String indexType;

  @Column(name="NON_UNIQUE")
  private String nonUnique;

  @Column(name="ORDINAL_POSITION")
  private int ordinalPosition;

  private int pages;

  @Column(name="TRIGGER_ID")
  private int triggerId;

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAscOrDesc() {
    return this.ascOrDesc;
  }

  public void setAscOrDesc(String ascOrDesc) {
    this.ascOrDesc = ascOrDesc;
  }

  public int getCardinality() {
    return this.cardinality;
  }

  public void setCardinality(int cardinality) {
    this.cardinality = cardinality;
  }

  public String getColumnName() {
    return this.columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public String getFilterCondition() {
    return this.filterCondition;
  }

  public void setFilterCondition(String filterCondition) {
    this.filterCondition = filterCondition;
  }

  public String getIndexName() {
    return this.indexName;
  }

  public void setIndexName(String indexName) {
    this.indexName = indexName;
  }

  public String getIndexQualifier() {
    return this.indexQualifier;
  }

  public void setIndexQualifier(String indexQualifier) {
    this.indexQualifier = indexQualifier;
  }

  public String getIndexType() {
    return this.indexType;
  }

  public void setIndexType(String indexType) {
    this.indexType = indexType;
  }

  public String getNonUnique() {
    return this.nonUnique;
  }

  public void setNonUnique(String nonUnique) {
    this.nonUnique = nonUnique;
  }

  public int getOrdinalPosition() {
    return this.ordinalPosition;
  }

  public void setOrdinalPosition(int ordinalPosition) {
    this.ordinalPosition = ordinalPosition;
  }

  public int getPages() {
    return this.pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public String getTableName() {
    return this.tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public int getTriggerId() {
    return this.triggerId;
  }

  public void setTriggerId(int triggerId) {
    this.triggerId = triggerId;
  }

  public boolean isEmpty() {
    return StringUtils.isEmpty(indexName);
  }

  
}