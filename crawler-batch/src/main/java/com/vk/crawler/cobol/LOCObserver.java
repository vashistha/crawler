package com.vk.crawler.cobol;

import java.util.Observable;
import java.util.Observer;

import com.vk.crawler.cobol.model.CodeAnalysisHelper;

public class LOCObserver implements Observer {

  public void update(Observable subject, Object arg) {
    CodeAnalysisHelper helper = null;
    if (arg instanceof CodeAnalysisHelper) {
      helper = (CodeAnalysisHelper) arg;
    }
    
    if (helper == null) {
      return;
    }

    if (helper.getTrimmedCodeLine().startsWith("*")) {
      helper.increamentCommentedLoc();
    }
    else {
      helper.increamentActiveLoc();
    }
  }
}
