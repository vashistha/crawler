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

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * **** USE (INHERIT) THIS CLASS, ONLY IF SPRING BEAN INJECTION or @Autowired IS REQUIRED IN JSF MANAGED BEAN ****  <br> 
 * --------------------------------------------------------------------------------------------------------------------------- <br>
 * 
 * http://www.beyondjava.net/blog/integrate-jsf-2-spring-3-nicely/
 * http://codereview.stackexchange.com/questions/23790/spring-autowiring-in-managed-beans-with-support-for-serialization-is-this-safe
 * Problems: 
 * 1. @Autowired in @ManagedBean does not work.
 * 2. Nor does @ViewScoped with @Controller.
 * 3. @ManagedProperty is not type safe and requires setter.
 * 4. You can not serialize singleton spring beans without hassle.
 * 5. Managed beans must be serializable so the container can serialize the session.
 * 
 */
public class SpringBeanInitializer {

  /**
   * Initialise the beans(Spring) with new application context when JSF bean get de-serialesed in session.  
   */
  @PostConstruct
  private void initContext() {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    ServletContext servletContext = (ServletContext) externalContext.getContext();
    WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).
                                 getAutowireCapableBeanFactory().
                                 autowireBean(this);
  }
  
  /**
   * Used to de-serialises the JSF bean and make sure autowired beans(spring) injected with new application context.
   * @param ois
   * @throws ClassNotFoundException
   * @throws IOException
   */
  private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
    ois.defaultReadObject();
    initContext();
  }
}
