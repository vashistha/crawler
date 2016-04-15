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

import com.vk.crawler.core.model.DataModel;
import com.vk.crawler.core.util.StringUtils;


public class FileProperties implements DataModel {
  
  private static final long serialVersionUID = 4609499834869706403L;
  
  private String fileName;
  private String relativePath;
  private String digest;
  
  
  public FileProperties() {
    super();
  }

  public FileProperties(String fileName, String absolutePath, String digest) {
    super();
    this.fileName = fileName;
    this.relativePath = absolutePath;
    this.digest = digest;
  }
  
  public String getFileName() {
    return fileName;
  }
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  
  public String getDigest() {
    return digest;
  }
  public void setDigest(String digest) {
    this.digest = digest;
  }
  
  public String getRelativePath() {
    return relativePath;
  }

  public void setRelativePath(String relativePath) {
    this.relativePath = relativePath;
  }
  
  public boolean isEmpty() {
    return StringUtils.isEmpty(fileName);
  }

  @Override
  public String toString() {
    return "<FileProperties><fileName>" + fileName
        + "</fileName><relativePath>" + relativePath
        + "</relativePath><digest>" + digest + "</digest></FileProperties>";
  }
  
}
