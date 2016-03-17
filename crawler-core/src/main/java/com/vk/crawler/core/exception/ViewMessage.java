/*
 * Copyright (C) 2015 by Vashistha kumar
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package com.vk.crawler.core.exception;

import com.vk.crawler.core.util.Constants.MessageSeverity;

public class ViewMessage extends Message {

  private String code;
  private String fieldId;
  
  public ViewMessage(MessageSeverity severity, String detailedMessage) {
    super(severity, detailedMessage);
  }
  
  public ViewMessage(MessageSeverity severity, String shortMessage, String detailedMessage) {
    super(severity, shortMessage, detailedMessage);
  }
  
  public ViewMessage(String shortMessage, String detailedMessage) {
    super(shortMessage, detailedMessage);
  }
  
  public ViewMessage(String shortMessage) {
    super(shortMessage);
  }

  public ViewMessage(MessageSeverity severity, String shortMessage, String detailedMessage, String code, String fieldId) {
    super(severity, shortMessage, detailedMessage);
    this.code = code;
    this.fieldId = fieldId;
  }
  
  public ViewMessage(MessageSeverity severity, String shortMessage, String detailedMessage, String fieldId) {
    super(severity, shortMessage, detailedMessage);
    this.fieldId = fieldId;
  }
  
  public ViewMessage(String detailedMessage, String code, String fieldId) {
    super(detailedMessage);
    this.code = code;
    this.fieldId = fieldId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getFieldId() {
    return fieldId;
  }

  public void setFieldId(String fieldId) {
    this.fieldId = fieldId;
  }

  @Override
  public String toString() {
    return "<LifeLineMessage><code>" + code + "</code><fieldId>" + fieldId + "</fieldId>" + super.toString() + "</LifeLineMessage>";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ViewMessage)) return false;
    if (!super.equals(o)) return false;

    ViewMessage message = (ViewMessage) o;

    if (code != null ? !code.equals(message.code) : message.code != null) return false;
    if (fieldId != null ? !fieldId.equals(message.fieldId) : message.fieldId != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (code != null ? code.hashCode() : 0);
    result = 31 * result + (fieldId != null ? fieldId.hashCode() : 0);
    return result;
  }
}
