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
@Table(name="VI_DB_CRAWLER_TYPE_INFO")
@TableGenerator(name = "typeInfo_entity", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "VI_DB_CRAWLER_TYPE_INFO", valueColumnName = "SEQ_COUNT", allocationSize = 1)
@NamedQuery(name="DBCrawlerTypeInfoEntity.findAll", query="SELECT d FROM DBCrawlerTypeInfoEntity d")
public class DBCrawlerTypeInfoEntity implements DataModel {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy=GenerationType.TABLE, generator = "typeInfo_entity")
  private Integer id;

  @Column(name="TYPE_NAME")
  private String typeName;
  
  @Column(name="AUTO_INCREMENT")
  private String autoIncrement;

  @Column(name="CASE_SENSITIVE")
  private String caseSensitive;

  @Column(name="CREATE_PARAMS")
  private String createParams;

  @Column(name="DATA_TYPE")
  private Integer dataType;

  private String nullable;

  private Integer precision;

  private String searchable;

  @Column(name="TRIGGER_ID")
  private Integer triggerId;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAutoIncrement() {
    return this.autoIncrement;
  }

  public void setAutoIncrement(String autoIncrement) {
    this.autoIncrement = autoIncrement;
  }

  public String getCaseSensitive() {
    return this.caseSensitive;
  }

  public void setCaseSensitive(String caseSensitive) {
    this.caseSensitive = caseSensitive;
  }

  public String getCreateParams() {
    return this.createParams;
  }

  public void setCreateParams(String createParams) {
    this.createParams = createParams;
  }

  public Integer getDataType() {
    return this.dataType;
  }

  public void setDataType(Integer dataType) {
    this.dataType = dataType;
  }

  public String getNullable() {
    return this.nullable;
  }

  public void setNullable(String nullable) {
    this.nullable = nullable;
  }

  public Integer getPrecision() {
    return this.precision;
  }

  public void setPrecision(Integer precision) {
    this.precision = precision;
  }

  public String getSearchable() {
    return this.searchable;
  }

  public void setSearchable(String searchable) {
    this.searchable = searchable;
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

  @Override
  public boolean isEmpty() {
    return StringUtils.isEmpty(typeName);
  }

  @Override
  public String toString() {
    return "<DBCrawlerTypeInfoEntity><id>" + id + "</id><typeName>" + typeName + "</typeName><autoIncrement>"
        + autoIncrement + "</autoIncrement><caseSensitive>" + caseSensitive + "</caseSensitive><createParams>"
        + createParams + "</createParams><dataType>" + dataType + "</dataType><nullable>" + nullable
        + "</nullable><precision>" + precision + "</precision><searchable>" + searchable + "</searchable><triggerId>"
        + triggerId + "</triggerId></DBCrawlerTypeInfoEntity>";
  }
}