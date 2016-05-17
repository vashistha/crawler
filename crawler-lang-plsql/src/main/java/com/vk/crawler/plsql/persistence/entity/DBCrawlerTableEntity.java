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
@Table(name="VI_DB_CRAWLER_TABLES")
@TableGenerator(name = "table_entity", table = "SEQUENCE", pkColumnName = "SEQ_NAME", pkColumnValue = "VI_DB_CRAWLER_TABLES", valueColumnName = "SEQ_COUNT", allocationSize = 1)
@NamedQuery(name="DBCrawlerTableEntity.findAll", query="SELECT d FROM DBCrawlerTableEntity d")
public class DBCrawlerTableEntity implements DataModel {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy=GenerationType.TABLE, generator = "table_entity")
  private Integer id;

  private String name;
  
  @Lob
  private String description;

  @Column(name="SCHEMA_ID")
  private Integer schemaId;

  @Column(name="TRIGGER_ID")
  private Integer triggerId;

  public DBCrawlerTableEntity() {
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getSchemaId() {
    return this.schemaId;
  }

  public void setSchemaId(Integer schemaId) {
    this.schemaId = schemaId;
  }

  public Integer getTriggerId() {
    return this.triggerId;
  }

  public void setTriggerId(Integer triggerId) {
    this.triggerId = triggerId;
  }

  public boolean isEmpty() {
    return StringUtils.isEmpty(name);
  }

  public String toString() {
    return "<DBCrawlerTableEntity><id>" + id + "</id><name>" + name + "</name><description>" + description
        + "</description><schemaId>" + schemaId + "</schemaId><triggerId>" + triggerId
        + "</triggerId></DBCrawlerTableEntity>";
  }
}