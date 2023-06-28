package com.phonebook.fw;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class ApplicationManager {

  String browser;
  public WebDriver driver;
  UserHelper user;
  ContactHelper contact;
  HomePageHelper homePage;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public UserHelper getUser() {
    return user;
  }

  public ContactHelper getContact() {
    return contact;
  }

  public HomePageHelper getHomePage() {
    return homePage;
  }

  public void init() {
    System.err.close(); // скрывает системные настройки при выводе в консоль
    if (browser.equalsIgnoreCase("chrome")){
      driver = new ChromeDriver();
    } else if (browser.equalsIgnoreCase("firefox")){
     // FirefoxOptions options = new FirefoxOptions();
      //options.addArguments("--remote-allow-origins=*");
      driver = new FirefoxDriver();
    } else if (browser.equalsIgnoreCase("edge")){
     // EdgeOptions options = new EdgeOptions();
      //options.addArguments("--remote-allow-origins=*");
      //driver=new EdgeDriver(options);
      driver=new EdgeDriver();
    }

    driver.get("https://telranedu.web.app");
    driver.manage().window().maximize();
    // wait for all elements on the site to load before starting test
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    user = new UserHelper(driver);
    contact = new ContactHelper(driver);
    homePage = new HomePageHelper(driver);
  }

  public void stop() {
    driver.quit();
  }

}
