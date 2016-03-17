package com.vk.crawler.cobol.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.vk.crawler.cobol.persistence.entity.CobolFileEntity;

@Transactional(transactionManager = "entityTransactionManager")
public class CobolFileDao {

  @PersistenceContext(unitName="crawler-batch")
  private EntityManager entityManager;

  public CobolFileEntity create(CobolFileEntity entity) {
    entityManager.persist(entity);
    entityManager.flush();
    return entity;
  }
}
