package com.example.websocketdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

  @RequestMapping("/page1")
  public String viewPage1() {
    return "viewPage1";
  }

}
