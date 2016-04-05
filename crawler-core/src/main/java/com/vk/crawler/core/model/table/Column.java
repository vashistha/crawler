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
