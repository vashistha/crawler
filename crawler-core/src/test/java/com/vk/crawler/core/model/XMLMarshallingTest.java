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

package com.vk.crawler.core.model;

import static org.junit.Assert.assertThat;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vk.crawler.core.CrawlerCoreTest;
import com.vk.crawler.core.model.table.Column;
import com.vk.crawler.core.model.table.Row;
import com.vk.crawler.core.model.table.Table;


public class XMLMarshallingTest extends CrawlerCoreTest {

  private static Logger logger = LoggerFactory.getLogger(XMLMarshallingTest.class);
  
  private static Table table;
 
  @BeforeClass
  public static void init() {
    logger.debug("Before class.");
    table = new Table();
    table.setTableName("New table");
    Column column = new Column();
    column.setValue("Hello");
    column.setColumnId(1);
    
    Row row = new Row();
    row.addColumn(column);
    row.setRowId(2);
    row.setHeader(true);
    table.addRow(row);
  }
  
  /**
   * Runs before each test
   */
  public void setUp() {
    
  }
  
  @Test
  public void testXMLMarshalling() throws JAXBException {
    JAXBContext jc = JAXBContext.newInstance(Table.class);
    
    Marshaller marshaller = jc.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    StringWriter stringWriter = new StringWriter();
    marshaller.marshal(table, stringWriter);
    //logger.debug(stringWriter.toString());
    
    
    assertThat(stringWriter.toString(), CoreMatchers.containsString("<row id=\"2\" header=\"true\" footer=\"false\">"));
    assertThat(stringWriter.toString(), CoreMatchers.containsString("<column id=\"1\" enabled=\"false\" render=\"false\" value=\"Hello\"/>"));
  } 
}
