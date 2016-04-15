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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vk.crawler.core.exception.CoreException;
import com.vk.crawler.core.exception.ErrorMessage;

@Repository
@Transactional
public class CrawlerTriggerDao {
  private static final Logger logger = LoggerFactory.getLogger(CrawlerTriggerDao.class);
  
  @PersistenceContext
  private EntityManager entityManager;


  public CrawlerTriggerEntity create(CrawlerTriggerEntity entity) {
    entityManager.persist(entity);
    entityManager.flush();
    return entity;
  }
  
  public CrawlerTriggerEntity update(CrawlerTriggerEntity entity) {
    entity = entityManager.merge(entity);
    entityManager.flush();
    return entity;
  }
  
  public CrawlerTriggerEntity update(CrawlerTriggerModel model) {
    CrawlerTriggerEntity entity = CrawlerTriggerEntity.valueOf(model);
    entity = entityManager.merge(entity);
    entityManager.flush();
    return entity;
  }
  
  public void delete(Integer triggerId) {
    try {
      CrawlerTriggerEntity entity = entityManager.find(CrawlerTriggerEntity.class, triggerId);
      if(entity != null) {
        entityManager.remove(entity);
      }
    }
    catch (NoResultException ne) {
      logger.error("No trigger found in database to be removed for Id="+triggerId);
    }
  }
  
  public List<CrawlerTriggerModel> select(String triggerName) throws CoreException {
    List<CrawlerTriggerModel> models = new ArrayList<CrawlerTriggerModel>();
    try {
      Query query = entityManager.createNamedQuery("CrawlerTriggerEntity.findByTriggerName");
      query.setParameter("triggerName", "%" + triggerName.toUpperCase() + "%");
      List<CrawlerTriggerEntity> entitis = (List<CrawlerTriggerEntity>)query.getResultList();
      if (entitis != null) {
        for (CrawlerTriggerEntity entity : entitis) {
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
  
  public CrawlerTriggerModel select(Integer triggerId) {
    CrawlerTriggerModel model = null;
    try {
      CrawlerTriggerEntity entity = entityManager.find(CrawlerTriggerEntity.class, triggerId);
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
