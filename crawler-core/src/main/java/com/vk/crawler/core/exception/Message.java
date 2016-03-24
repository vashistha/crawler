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

package com.vk.crawler.core.exception;

import com.vk.crawler.core.util.Constants.MessageSeverity;



public class Message {
  private MessageSeverity severity;
  private String shortMessage;
  private String detailedMessage;
  
  protected Message(MessageSeverity severity, String shortMessage, String detailedMessage) {
    super();
    this.severity = severity;
    this.shortMessage = shortMessage;
    this.detailedMessage = detailedMessage;
  }
  
  protected Message(MessageSeverity severity, String detailedMessage) {
    super();
    this.severity = severity;
    this.detailedMessage = detailedMessage;
  }
  
  protected Message(String shortMessage, String detailedMessage) {
    super();
    this.shortMessage = shortMessage;
    this.detailedMessage = detailedMessage;
  }

  protected Message(String shortMessage) {
    super();
    this.shortMessage = shortMessage;
  }

  public MessageSeverity getSeverity() {
    return severity;
  }

  public void setSeverity(MessageSeverity severity) {
    this.severity = severity;
  }

  public String getShortMessage() {
    return shortMessage;
  }

  public void setShortMessage(String shortMessage) {
    this.shortMessage = shortMessage;
  }

  public String getDetailedMessage() {
    return detailedMessage;
  }

  public void setDetailedMessage(String detailedMessage) {
    this.detailedMessage = detailedMessage;
  }

  @Override
  public String toString() {
    return "<Message><severity>" + severity + "</severity><shortMessage>"
        + shortMessage + "</shortMessage><detailedMessage>" + detailedMessage
        + "</detailedMessage></Message>";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Message)) return false;

    Message message = (Message) o;

    if (detailedMessage != null ? !detailedMessage.equals(message.detailedMessage) : message.detailedMessage != null)
      return false;
    if (severity != message.severity) return false;
    if (shortMessage != null ? !shortMessage.equals(message.shortMessage) : message.shortMessage != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = severity != null ? severity.hashCode() : 0;
    result = 31 * result + (shortMessage != null ? shortMessage.hashCode() : 0);
    result = 31 * result + (detailedMessage != null ? detailedMessage.hashCode() : 0);
    return result;
  }
}
