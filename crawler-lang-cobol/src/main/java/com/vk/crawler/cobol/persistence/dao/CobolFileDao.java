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
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vk.crawler.cobol.model.CobolFileProperties;
import com.vk.crawler.cobol.persistence.entity.CobolFileEntity;

@Repository
@Transactional
public class CobolFileDao {

  @PersistenceContext
  private EntityManager entityManager;

  public CobolFileEntity create(CobolFileEntity entity) {
    entityManager.persist(entity);
    entityManager.flush();
    return entity;
  }
  
  public int deleteById(Integer id) {
    int deletedRows = 0;
    CobolFileEntity entity = entityManager.find(CobolFileEntity.class, id);
    if(entity != null) {
      entityManager.remove(entity);
      deletedRows = 1;
    }
    return deletedRows;
  }
  
  
  public int deleteByTriggerId(Integer triggerId) {
    int deletedRows = 0;
    Query query = entityManager.createNamedQuery("CobolFileEntity.deleteByTriggerId");
    query.setParameter("triggerId", triggerId);
    deletedRows = query.executeUpdate();
    return deletedRows;
  }
  
  public List<CobolFileProperties> selectByTriggerId(Integer triggerId) {
    List<CobolFileProperties> files = new ArrayList<>();
    
    Query query = entityManager.createNamedQuery("CobolFileEntity.findByTriggerId");
    query.setParameter("triggerId", triggerId);
    List<CobolFileEntity> entities = query.getResultList();
    if(entities != null) {
      for (CobolFileEntity entity : entities) {
        files.add(entity.toModel());
      }
    }
    return files;
  }
}
