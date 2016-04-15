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

package com.vk.crawler.cobol.analyser;

import java.util.List;
import java.util.Observable;

import com.vk.crawler.cobol.model.CobolFileCodeMetrics;
import com.vk.crawler.cobol.model.CodeAnalysisHelper;

public class SourceCodeAnalyser extends Observable {

  /**
   * List of observers can be added by DI using spring config.
   */
  public SourceCodeAnalyser() {
    super();
    addObserver(new DescriptionObserver());
    addObserver(new LOCObserver());
  }

  /**
   * Analyses the code lines passed as <code>codeLines</code> and then returns {@link CobolFileCodeMetrics}.
   * Observer pattern is used to get different aspect of analysis such as number of lines of code or file description
   * available inside code. 
   * @param codeLines
   * @return {@link CobolFileCodeMetrics}
   */
  public CobolFileCodeMetrics analyse(List<String> codeLines) {
    CobolFileCodeMetrics codeMetrics = new CobolFileCodeMetrics();
    CodeAnalysisHelper helper = new CodeAnalysisHelper(codeMetrics);
    /*
     * By default observers will be notified one by one.
     * notifyObservers can be overridden to invoke each observer in independent thread. This will improve
     * performance of the analyser but make sure an observer is changed to process each codeLine in correct
     * sequence.  
     */
    for (String codeLine : codeLines) {
      helper.setCodeLine(codeLine);
      setChanged();
      notifyObservers(helper);
    }
    return codeMetrics;
  }
}
