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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.vk.crawler.core.model.ViewModel;

@XmlRootElement(name="column")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Column extends ViewModel {

  private Integer columnId;
  
  @XmlAttribute(name="id")
  public Integer getColumnId() {
    return columnId;
  }

  public void setColumnId(Integer columnId) {
    this.columnId = columnId;
  }

  @Override
  public String toString() {
    return "<Column><columnId>" + columnId + "</columnId><getValue()>" + getValue() + "</getValue()><getType()>"
        + getType() + "</getType()><getStyle()>" + getStyle() + "</getStyle()><getStyleClass()>" + getStyleClass()
        + "</getStyleClass()><getTitle()>" + getTitle() + "</getTitle()><isRender()>" + isRender()
        + "</isRender()><isEnabled()>" + isEnabled() + "</isEnabled()><toString()>" + super.toString()
        + "</toString()><getClass()>" + getClass() + "</getClass()><hashCode()>" + hashCode()
        + "</hashCode()></Column>";
  }
}
