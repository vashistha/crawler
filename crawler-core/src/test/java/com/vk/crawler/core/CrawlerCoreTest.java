package com.vk.crawler.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:test-spring-context.xml"})
public class CrawlerCoreTest {

  @Test
  public void testContextLoad() {
    
  }
}
