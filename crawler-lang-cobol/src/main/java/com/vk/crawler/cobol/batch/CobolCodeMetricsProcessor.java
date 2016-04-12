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

package com.vk.crawler.cobol.batch;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import com.vk.crawler.cobol.analyser.SourceCodeAnalyser;
import com.vk.crawler.cobol.model.CobolFileCodeMetrics;
import com.vk.crawler.cobol.persistence.entity.CobolFileCodeMetricsEntity;
import com.vk.crawler.cobol.persistence.entity.CobolFileEntity;
import com.vk.crawler.core.util.FileUtils;

@StepScope
public class CobolCodeMetricsProcessor implements ItemProcessor<CobolFileEntity, CobolFileCodeMetricsEntity> {

  private static final Logger logger = LoggerFactory.getLogger(CobolCodeMetricsProcessor.class);
  
  @Value("#{jobParameters['rootDirectory']}")
  private String rootDirectory;
  
  @Value("#{jobParameters['triggerId']}")
  private Integer triggerId;
  
  public CobolFileCodeMetricsEntity process(CobolFileEntity fileEntity) throws Exception {
    logger.debug("Processing file-", fileEntity.getRelativePath() + File.separator + fileEntity.getFileName());
    
    CobolFileCodeMetrics model = process(rootDirectory + File.separator + fileEntity.getRelativePath() + File.separator + fileEntity.getFileName());
    
    CobolFileCodeMetricsEntity entity = CobolFileCodeMetricsEntity.valueOf(model);
    entity.setCobolFileId(fileEntity.getId());
    entity.setTriggerId(triggerId);
    return entity;
  }
  
  public CobolFileCodeMetrics process(String fileNameWithAbsolutePath) {
    
    List<String> codeLines = FileUtils.readLines(fileNameWithAbsolutePath); 
    SourceCodeAnalyser analyser = new SourceCodeAnalyser();
    CobolFileCodeMetrics model = analyser.analyse(codeLines);
    return model;
  } 
}
