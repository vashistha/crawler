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
@Table(name="VI_DB_CRAWLER_KEY")
@TableGenerator(name = "tableKey_entity", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "VI_DB_CRAWLER_KEY", valueColumnName = "SEQ_COUNT", allocationSize = 1)
@NamedQuery(name="DBCrawlerTablesKeysEntity.findAll", query="SELECT d FROM DBCrawlerTablesKeysEntity d")
public class DBCrawlerTablesKeysEntity implements DataModel {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy=GenerationType.TABLE, generator = "tableKey_entity")
  private Integer id;

  @Column(name="COLUMN_NAME")
  private String columnName;

  private String deferrability;

  @Column(name="DELETE_RULE")
  private String deleteRule;

  @Column(name="KEY_NAME")
  private String keyName;

  @Column(name="KEY_SEQ")
  private Integer keySeq;

  @Column(name="KEY_TYPE")
  private String keyType;

  @Column(name="REFERENCING_COLUMN_NAME")
  private String referencingColumnName;

  @Column(name="UPDATE_RULE")
  private String updateRule;

  @Column(name="TRIGGER_ID")
  private Integer triggerId;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getColumnName() {
    return this.columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public String getDeferrability() {
    return this.deferrability;
  }

  public void setDeferrability(String deferrability) {
    this.deferrability = deferrability;
  }

  public String getDeleteRule() {
    return this.deleteRule;
  }

  public void setDeleteRule(String deleteRule) {
    this.deleteRule = deleteRule;
  }

  public String getKeyName() {
    return this.keyName;
  }

  public void setKeyName(String keyName) {
    this.keyName = keyName;
  }

  public Integer getKeySeq() {
    return this.keySeq;
  }

  public void setKeySeq(Integer keySeq) {
    this.keySeq = keySeq;
  }

  public String getKeyType() {
    return this.keyType;
  }

  public void setKeyType(String keyType) {
    this.keyType = keyType;
  }

  public String getReferencingColumnName() {
    return this.referencingColumnName;
  }

  public void setReferencingColumnName(String referencingColumnName) {
    this.referencingColumnName = referencingColumnName;
  }

  public Integer getTriggerId() {
    return this.triggerId;
  }

  public void setTriggerId(Integer triggerId) {
    this.triggerId = triggerId;
  }

  public String getUpdateRule() {
    return this.updateRule;
  }

  public void setUpdateRule(String updateRule) {
    this.updateRule = updateRule;
  }

  public boolean isEmpty() {
    return StringUtils.isEmpty(keyName);
  }

  public String toString() {
    return "<DBCrawlerTablesKeysEntity><id>" + id + "</id><columnName>" + columnName + "</columnName><deferrability>"
        + deferrability + "</deferrability><deleteRule>" + deleteRule + "</deleteRule><keyName>" + keyName
        + "</keyName><keySeq>" + keySeq + "</keySeq><keyType>" + keyType + "</keyType><referencingColumnName>"
        + referencingColumnName + "</referencingColumnName><updateRule>" + updateRule + "</updateRule><triggerId>"
        + triggerId + "</triggerId></DBCrawlerTablesKeysEntity>";
  }

}