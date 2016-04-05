package com.vk.crawler.core.model;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vk.crawler.core.CrawlerCoreTest;
import com.vk.crawler.core.model.table.Table;


public class XMLUnMarshallingTest extends CrawlerCoreTest {

  private static Logger logger = LoggerFactory.getLogger(XMLUnMarshallingTest.class);
  
  private static Table table;
  private static String file = "JAXBTest.xml";
  
 
  @Test
  public void testXMLUnMarshalling() throws JAXBException, URISyntaxException {
    
    JAXBContext jaxbContext = JAXBContext.newInstance(Table.class);

    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    
    URL url = XMLUnMarshallingTest.class.getResource(file);
    
    table = (Table) jaxbUnmarshaller.unmarshal(new File(url.toURI()));
    
    logger.debug(table.toString());
    assertThat(table, CoreMatchers.instanceOf(Table.class));
    assertTrue(table.getTableName().equals("techStack"));
    assertTrue(table.getRows().size() == 2);
  }
}
