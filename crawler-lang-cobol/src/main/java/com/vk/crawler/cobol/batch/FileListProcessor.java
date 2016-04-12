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
 */E.
 */

package com.vk.crawler.cobol.batch;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;

import com.vk.crawler.cobol.model.FileProperties;
import com.vk.crawler.cobol.persistence.entity.CobolFileEntity;

@StepScope
public class FileListProcessor implements ItemProcessor<FileProperties, CobolFileEntity> {

  private static final Logger logger = LoggerFactory.getLogger(FileListProcessor.class);
  
  @Value("#{jobParameters['rootDirectory']}")
  private String rootDirectory;
  
  @Value("#{jobParameters['triggerId']}")
  private Integer triggerId;
  
  public CobolFileEntity process(FileProperties file) throws Exception {
    CobolFileEntity entity = new CobolFileEntity();
    
    entity.setFileName(file.getFileName());
    entity.setRelativePath(file.getRelativePath());
    entity.setTriggerId(triggerId);
    try {
      entity.setDigest(DigestUtils.md5DigestAsHex(new FileInputStream(rootDirectory + File.separator + file.getRelativePath() + File.separator + file.getFileName())));
    }
    catch (IOException e) {
      logger.error("Exception in preparing digest", e);
    }
    return entity;
  }
  
}
