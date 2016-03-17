/*
 * Copyright (C) 2015 by Vashistha kumar
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package com.vk.crawler.core.util.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class PersistenceHelper {

  private static Boolean lock = new Boolean(true);
  private static Map<String, EntityManagerFactory> emfMap = new HashMap<String, EntityManagerFactory>();
  
  protected Logger logger = LoggerFactory.getLogger(getClass());
  
  private EntityManager em;
  
  public PersistenceHelper(String persistenceUnit) {
    this(persistenceUnit, false);
  }

  public PersistenceHelper(String persistenceUnit, boolean readOnly) {
    initialize(persistenceUnit);
    openEm(persistenceUnit, readOnly);
  }

  public void openEm(String persistenceUnit, boolean readOnly) {
    if (em != null) {
      return;
    }
    em = emfMap.get(persistenceUnit).createEntityManager();
    if (readOnly == false) {
      em.getTransaction().begin();
    }
  }

  public EntityManager getActiveEm(){
    if(em == null){
      throw new IllegalStateException("No transaction was active!");
    }
    return em;
  }

  public void closeEm(){
    if(em == null){
      return;
    }
    try{
      if(em.getTransaction().isActive()){
        if(em.getTransaction().getRollbackOnly()){
          em.getTransaction().rollback();
        }
        else {
          em.getTransaction().commit();
        }
      }
    }
    finally {
      em.close();
      em = null;
    }
  }

  public void markRollback(){
    if(em != null){
      em.getTransaction().setRollbackOnly();
    }
  }
  
  public boolean isRollbackOnly(){
    return em != null && em.getTransaction().getRollbackOnly();
  }

  private void initialize(String persistenceUnit){
    if(emfMap.get(persistenceUnit) !=null ){
      return;
    }
    synchronized(lock){
      try{
        emfMap.put(persistenceUnit, Persistence.createEntityManagerFactory(persistenceUnit));
      } catch(Throwable t){
        logger.error("Failed to setup persistence unit"+ persistenceUnit, t);
        return;
      }
    }
  }
}