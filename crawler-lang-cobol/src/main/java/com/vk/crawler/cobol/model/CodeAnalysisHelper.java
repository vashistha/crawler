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

package com.vk.crawler.cobol.model;

import com.vk.crawler.core.util.StringUtils;

public class CodeAnalysisHelper {
  private CobolFileCodeMetrics codeMetrics;
  private String codeLine;
  private int lineNumber;
  
  
  public CodeAnalysisHelper(CobolFileCodeMetrics codeMetrics) {
    super();
    this.codeMetrics = codeMetrics;
  }
  
  public CobolFileCodeMetrics getCodeMetrics() {
    return codeMetrics;
  }
  
  public String getCodeLine() {
    return codeLine;
  }
  
  public void setCodeLine(String codeLine) {
    this.codeLine = codeLine;
  }
  
  public int getLineNumber() {
    return lineNumber;
  }
  
  public void setLineNumber(int lineNumber) {
    this.lineNumber = lineNumber;
  }

  public void increamentCommentedLoc() {
    codeMetrics.increamentCommentedLoc();
  }

  public void increamentActiveLoc() {
    codeMetrics.increamentActiveLoc();
  }
  
  public void increamentOtherLoc() {
    codeMetrics.increamentOtherLoc();
  }
  
  public String getTrimmedCodeLine() {
    return codeLine == null ? null : codeLine.trim();
  }
  
  public String getCodeLineWithoutCommentChar() {
    return codeLine == null ? "" : StringUtils.replace(codeLine, "*", "").trim();
  }
}
