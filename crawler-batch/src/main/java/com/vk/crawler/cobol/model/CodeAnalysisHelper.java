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
  
  public String getTrimmedCodeLine() {
    return codeLine == null ? null : codeLine.trim();
  }
  
  public String getCodeLineWithoutCommentChar() {
    return codeLine == null ? "" : StringUtils.replace(codeLine, "*", "").trim();
  }
}
