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

package com.vk.example.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vk.crawler.cobol.model.CobolFileProperties;
import com.vk.crawler.cobol.persistence.dao.CobolFileDao;
import com.vk.crawler.cobol.persistence.entity.CobolFileEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/cobol-spring-context.xml", "classpath:/persistence-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CobolFileDaoTest {
  
  private static final Logger logger = LoggerFactory.getLogger(CobolFileDaoTest.class);
  
  @Autowired
  private CobolFileDao cobolFileDao;
  
  private CobolFileProperties fileProp;
  
  @Before
  public void setUp() {
    fileProp = new CobolFileProperties();
    fileProp.setFileName("ABCD");
    fileProp.setRelativePath("none");
    fileProp.setDigest("AE45C4");
    fileProp.setTriggerId(3);
  }
  
  @Test
  public void test1_CreateFile() {
    CobolFileEntity entity = CobolFileEntity.valueOf(fileProp);
    cobolFileDao.create(entity);
    assertNotNull(entity.getId());
  }
  
  @Test
  public void test2_SelectByTriggerId() {
    List<CobolFileProperties> files = cobolFileDao.selectByTriggerId(3);
    assertTrue(files.size() == 1);
  }
  
  @Test
  public void test3_DeleteFileByTriggerId() {
    int deletedRows = cobolFileDao.deleteByTriggerId(3);
    logger.debug("***************************************{}", deletedRows);
    assertTrue(deletedRows == 1);
  }
}
