package com.vk.crawler.cobol;

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
