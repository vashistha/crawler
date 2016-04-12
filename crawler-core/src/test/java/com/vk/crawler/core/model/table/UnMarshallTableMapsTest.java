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

package com.vk.crawler.core.model.table;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vk.crawler.core.CrawlerCoreTest;

public class UnMarshallTableMapsTest extends CrawlerCoreTest {

  private static Logger logger = LoggerFactory.getLogger(UnMarshallTableMapsTest.class);
  
  private static TableMaps tableMaps;
  private static String file = "tableMaps.xml";
  
  @Test
  public void testTableUnMarshalling() throws JAXBException, URISyntaxException {
    
    JAXBContext jaxbContext = JAXBContext.newInstance(TableMaps.class);

    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    
    URL url = UnMarshallTableMapsTest.class.getResource(file);
    
    tableMaps = (TableMaps) jaxbUnmarshaller.unmarshal(new File(url.toURI()));
    
    assertThat(tableMaps, instanceOf(TableMaps.class));
    assertThat(tableMaps.getTable("testStack"), instanceOf(Table.class));
    assertNull(tableMaps.getTable("tech1Stack"));
    assertTrue(tableMaps.getTables().size() == 2);
    assertTrue(tableMaps.getTable("testStack").getTableName().equals("testStack"));
  }
}
