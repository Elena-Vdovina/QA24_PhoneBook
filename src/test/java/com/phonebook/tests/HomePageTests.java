package com.phonebook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase{

  @Test
  public void openHomeTest(){
      // System.out.println("Home Components is " + isHomeComponentPresent1());
       //h1[.='Home Component']))
    Assert.assertTrue(app.getHomePage().isHomeComponentPresent());
  }

}
