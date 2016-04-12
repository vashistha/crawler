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

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.vk.crawler.cobol.model.CodeAnalysisHelper;
import com.vk.crawler.core.util.StringUtils;

public class DescriptionObserver implements Observer {

  private boolean descriptionStartInd;
  private boolean descriptionEndInd;
  
  private List<String> descriptionFilterString;
  private List<String> descriptionStartInds;
  private List<String> descriptionEndsInds;
  
  public DescriptionObserver() {
    /**
     * These variables can be initialised by DI using spring.
     */
    descriptionStartInds = new ArrayList<String>() {{
      add("DATE-COMPILED");
      add("PROGRAM-ID");}};
       
    descriptionEndsInds = new ArrayList<String>() {{ 
      add("ENVIRONMENT DIVISION.");
      add("CONFIGURATION SECTION.");
      add("SOURCE-COMPUTER.");
      }};
    
    descriptionFilterString = new ArrayList<String>() {{
      add("DATE-COMPILED.");
      add("*   Do NOT add the date to the Field DATE-COMPILED. It will");
      add("*   be added to by the MF Cobol function WHEN-COMPILED when");
      add("*   the program is compiled.");}};
    descriptionFilterString.addAll(descriptionEndsInds);
  }
  
  public void update(Observable sub, Object arg) {
    CodeAnalysisHelper helper = null;
    SourceCodeAnalyser subject = null;
    
    if (arg instanceof CodeAnalysisHelper) {
      helper = (CodeAnalysisHelper) arg;
    }
    if (sub instanceof SourceCodeAnalyser) {
      subject = (SourceCodeAnalyser) sub;
    }
    if (helper == null || subject == null) {
      return;
    }
    
    /*
     *Currently "!envtDivisionFound" check is redundant because observer is removed from subject.  
     **/
    if(descriptionStartInd && !descriptionEndInd) {
      if(descriptionEndsInds.contains(helper.getTrimmedCodeLine())) {
        descriptionEndInd = true;
        subject.deleteObserver(this);
      }
      else if (!descriptionFilterString.contains(helper.getTrimmedCodeLine())){
        helper.getCodeMetrics().appendDescription(StringUtils.endWithNewLineChar(helper.getTrimmedCodeLine()));
      }
    }
    else if (!descriptionStartInd) {
      for (String strInd : descriptionStartInds) {
        if(helper.getTrimmedCodeLine().startsWith(strInd)) {
          descriptionStartInd = true;
          break;
        }
      }
    }
  }
}
