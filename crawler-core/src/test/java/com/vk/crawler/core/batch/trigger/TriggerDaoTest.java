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

package com.vk.crawler.core.batch.trigger;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vk.crawler.core.util.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-context.xml", "classpath:/persistence-context.xml"})
public class TriggerDaoTest {
  private static final Logger logger = LoggerFactory.getLogger(TriggerDaoTest.class);
  
  @Autowired
  private CrawlerTriggerDao triggerDao;
  
  private String triggerName = "Test Unit";
  
  @Test
  public void testSelectByTriggerName() {
    try {
      List<CrawlerTriggerModel> models = triggerDao.select(triggerName);
      
      if(models != null) {
        //As trigger name is unique then select by trigger name must returns only one result or null
        assertTrue(models.size() == 1);
      }
    }
    catch (Exception e) {
      logger.error("Error in testSelectByTriggerName:", e);
    }
  }

  @Test
  public void testDeleteTrigger() {
    try {
      List<CrawlerTriggerModel> models = triggerDao.select(triggerName);
      
      if(models != null) {
        for (CrawlerTriggerModel model : models) {
          triggerDao.delete(model.getId());
          logger.debug("Deleted trigger :{}", model.getTriggerName());
          
          assertNull(triggerDao.select(model.getId()));
        }
      }
    }
    catch (Exception e) {
      logger.error("Error in testDeleteTrigger:", e);
    }
  }
  
  @Test
  public void testCreateTrigger() {
    try {
      CrawlerTriggerEntity entity = new CrawlerTriggerEntity();
      entity.setTriggerName(triggerName);
      entity.setDescription("Unit testing");
      entity.setPath("");
      entity.setStatus("COMPLETED");;
      entity.setProcessingTime(0);;
      entity.setCreatedBy("Java Unit");
      entity.setCreatedTs(DateUtils.getCurrentDate());;
      triggerDao.create(entity);
      assertNotNull(entity.getId());
    } 
    catch (Exception e) {
      logger.error("Error in testCreateTrigger:", e);
    }
  }
}
