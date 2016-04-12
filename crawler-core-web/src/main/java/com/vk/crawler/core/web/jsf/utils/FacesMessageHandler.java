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

package com.vk.crawler.core.web.jsf.utils;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vk.crawler.core.exception.ViewMessage;
import com.vk.crawler.core.util.Constants;



public class FacesMessageHandler {
  
  public static final Logger logger = LoggerFactory.getLogger(FacesMessageHandler.class.getClass().getName());
  
  public static void addMessageInContext(ViewMessage msg) {
    
    try {
      FacesMessage fcsMsg = new FacesMessage(msg.getShortMessage(), msg.getDetailedMessage());
      Constants.MessageSeverity severity = msg.getSeverity();
      logger.info("Constructing FacesMessage : " + msg);
      switch(severity) {
        case FATAL : 
          fcsMsg.setSeverity(javax.faces.application.FacesMessage.SEVERITY_FATAL);
          break;
        case ERROR :
          fcsMsg.setSeverity(javax.faces.application.FacesMessage.SEVERITY_ERROR);
          break;
        case WARN :
          fcsMsg.setSeverity(javax.faces.application.FacesMessage.SEVERITY_WARN);
          break;
        case INFO :
          fcsMsg.setSeverity(javax.faces.application.FacesMessage.SEVERITY_INFO);
          break;
      }
      FacesContext.getCurrentInstance().addMessage(msg.getFieldId(), fcsMsg);
    }
    catch (Exception e) {
      logger.debug(e.getMessage());
    }
  }
  
  public static void addMessageInContext(List<ViewMessage> msList) {
    if(msList != null) {
      for(ViewMessage msg : msList) {
        addMessageInContext(msg);
      }
    }
  }
  
  public static void clearMessageFromContext() {
    Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages();
    while (iter.hasNext()) {
      iter.next();
      iter.remove();
    }
  }
  
}
