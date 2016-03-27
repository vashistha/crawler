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

package com.vk.crawler.cobol.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.vk.crawler.cobol.batch.model.CobolCrawlerTrigger;
import com.vk.crawler.cobol.persistence.entity.CobolCrawlerTriggerEntity;
import com.vk.crawler.core.exception.CoreException;
import com.vk.crawler.core.exception.ErrorMessage;

@Transactional
public class CobolCrawlerTriggerDao {
  private static final Logger logger = LoggerFactory.getLogger(CobolCrawlerTriggerDao.class);
  
  @PersistenceContext(unitName="crawler-batch")
  private EntityManager entityManager;


  public CobolCrawlerTriggerEntity create(CobolCrawlerTriggerEntity entity) {
    entityManager.persist(entity);
    entityManager.flush();
    return entity;
  }
  
  public CobolCrawlerTriggerEntity update(CobolCrawlerTriggerEntity entity) {
    entity = entityManager.merge(entity);
    entityManager.flush();
    return entity;
  }
  
  public CobolCrawlerTriggerEntity update(CobolCrawlerTrigger model) {
    CobolCrawlerTriggerEntity entity = CobolCrawlerTriggerEntity.valueOf(model);
    entity = entityManager.merge(entity);
    entityManager.flush();
    return entity;
  }
  
  public void delete(Integer triggerId) {
    try {
      CobolCrawlerTriggerEntity entity = entityManager.find(CobolCrawlerTriggerEntity.class, triggerId);
      if(entity != null) {
        entityManager.remove(entity);
      }
    }
    catch (NoResultException ne) {
      logger.error("No trigger found in database to be removed for Id="+triggerId);
    }
  }
  
  public List<CobolCrawlerTrigger> select(String triggerName) throws CoreException {
    List<CobolCrawlerTrigger> models = new ArrayList<CobolCrawlerTrigger>();
    try {
      Query query = entityManager.createNamedQuery("CobolCrawlerTriggerEntity.findByTriggerName");
      query.setParameter("triggerName", "%" + triggerName.toUpperCase() + "%");
      List<CobolCrawlerTriggerEntity> entitis = (List<CobolCrawlerTriggerEntity>)query.getResultList();
      if (entitis != null) {
        for (CobolCrawlerTriggerEntity entity : entitis) {
          models.add(entity.toModel());
        }
      }
    }
    catch (NoResultException ne) {
      logger.error("No result found with name="+triggerName);
    }
    catch (Exception e) {
      throw new CoreException(ErrorMessage.MSG_ERROR, e);
    }
    
    return models;
  }
  
  public CobolCrawlerTrigger select(Integer triggerId) {
    CobolCrawlerTrigger model = null;
    try {
      CobolCrawlerTriggerEntity entity = entityManager.find(CobolCrawlerTriggerEntity.class, triggerId);
      if (entity != null) {
        model = entity.toModel();
      }
    }
    catch (NoResultException ne) {
      logger.error("No result found for triggerId="+triggerId);
    }
    return model;
  }
}
