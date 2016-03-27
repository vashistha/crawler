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

package com.vk.crawler.core.pattern.observer;

import java.util.Observable;

import com.vk.crawler.core.exception.CoreException;


public abstract class Subject extends Observable {
  
  /**Register an observer with this subject.*/
  public void register(java.util.Observer obj) {
    super.addObserver(obj);
  }
  
  /**Unregister an observer with this subject.*/
  public void unregister(java.util.Observer obj) {
    super.deleteObserver(obj);
  }

  /**Notify observer(s) before process starts in the subject.*/
  public abstract void notifyObservers_preProcess() throws CoreException;
  
  /**Notify observer(s) after process ends in the subject*/
  public abstract void notifyObservers_postProcess() throws CoreException;

  /**Called from observer to get updates from the subject. Observer specific update.*/
  public abstract Object getUpdate(Observer obj);
  
  /**Called from observer to get updates from the subject. Generic update.*/
  public abstract Object getUpdate();
  
}
