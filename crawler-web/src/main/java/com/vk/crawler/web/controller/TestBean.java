package com.vk.crawler.web.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class TestBean implements Serializable {
  
  private String hello;
  
  public String getHello() {
    hello ="Hello from PrimeFaces and Spring Boot!";
    System.out.println("Hello called.");
    return hello;
  }

  public void setHello(String hello) {
    this.hello = hello;
  }
}
