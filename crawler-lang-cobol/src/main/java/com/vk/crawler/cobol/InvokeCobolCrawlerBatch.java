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

package com.vk.crawler.cobol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

import com.vk.crawler.core.batch.CrawlerConstant.TriggerStatus;
import com.vk.crawler.core.batch.trigger.CrawlerTriggerModel;
import com.vk.crawler.core.batch.trigger.CrawlerTriggerDao;
import com.vk.crawler.core.util.DateUtils;
import com.vk.crawler.core.util.Timer;

public class InvokeCobolCrawlerBatch {

  private static final Logger logger = LoggerFactory.getLogger(InvokeCobolCrawlerBatch.class);
  private static final Timer timer = new Timer("InvokeCobolCrawler");
  
  @Autowired
  private CrawlerTriggerDao cobolCrawlerTriggerDao;
  
  @Autowired
  private JobLauncher jobLauncher;
  
  @Autowired
  private Job cobolFileAnalyserJob;
  
  public void start(Integer triggerId) throws Exception {

    try {
      CrawlerTriggerModel trigger = cobolCrawlerTriggerDao.select(triggerId);
      
      if(trigger == null || trigger.isEmpty()) {
        logger.debug("Trigger not found");
        return;
      }
      
      JobParametersBuilder paramBuilder = new JobParametersBuilder();
      //paramBuilder.addString("rootDirectory", "src\\main\\resources");
      paramBuilder.addString("rootDirectory",  trigger.getPath());
      paramBuilder.addLong("triggerId", triggerId.longValue());
      paramBuilder.addLong("time", DateUtils.getCurrentDate().getTime());
      JobParameters jobParameters = paramBuilder.toJobParameters();
      
      updateTrigger(trigger, TriggerStatus.STARTED.getValue() , null);
      
      timer.start(); 
      JobExecution execution = jobLauncher.run(cobolFileAnalyserJob, jobParameters);
      timer.end();
      
      logger.debug("Exit Status : " + execution.getStatus() + " and Time taken : " + timer.getTotalTime());
      updateTrigger(trigger, execution.getStatus().toString(), Integer.valueOf(timer.getTotalTime()+""));
    } 
    catch (Exception e) {
      logger.error("Error in cobol crawler for trigger id = " + triggerId);
      throw e;
    }
  }
  
  public void updateTrigger(CrawlerTriggerModel trigger, String status, Integer timeElapsed) {
    trigger.setStatus(status);
    trigger.setUpdatedTs(DateUtils.getCurrentDate());
    trigger.setUpdatedBy("cobolFileAnalyserJob");
    trigger.setProcessingTime(timeElapsed);
    cobolCrawlerTriggerDao.update(trigger);
  }
}
