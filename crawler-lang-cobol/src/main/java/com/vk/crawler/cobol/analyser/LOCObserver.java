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

import java.util.Observable;
import java.util.Observer;

import com.vk.crawler.cobol.model.CodeAnalysisHelper;
import com.vk.crawler.core.util.StringUtils;

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
    else if (!StringUtils.isEmpty(helper.getTrimmedCodeLine())){
      helper.increamentActiveLoc();
    }
    else {
      helper.increamentOtherLoc();
    }
  }
}
