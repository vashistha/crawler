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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;


public class CoreException extends Exception {
  private static final long serialVersionUID = 5682656696815827470L;
  private List<ViewMessage> messages = new ArrayList<ViewMessage>();
  
  public CoreException() {
    
  }

  public CoreException(String message) {
    super(message);
  }

  public CoreException(Throwable cause) {
    super(cause);
    
  }

  public CoreException(String message, Throwable cause) {
    super(message, cause);
    
  }

  public CoreException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
  
  public CoreException(ViewMessage message) {
    super();
    this.messages.add(message);
  }
  
  public CoreException(ViewMessage message, Throwable cause) {
    super(cause);
    this.messages.add(message);
  }
  
  public CoreException(List<ViewMessage> messages) {
    super();
    this.messages = messages;
  }
  
  public List<ViewMessage> getMessages() {
    return messages;
  }
  
  public String printExceptionTrace() {
    StringWriter sw = new StringWriter();
    this.printStackTrace(new PrintWriter(sw));
    return sw.toString();
  }
}
