package com.phonebook.fw;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseHelper {

  public WebDriver driver;

  public BaseHelper(WebDriver driver) {
    this.driver = driver;
  }

  public boolean isElementPresent(By locator) {
    return driver.findElements(locator).size() > 0;
  }

  public boolean isElementPresent1(By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }

  public void type(By locator, String text) {
    if (text != null) {
      click(locator);
      driver.findElement(locator).clear();
      driver.findElement(locator).sendKeys(text);
    }
  }

  public void click(By locator) {
    driver.findElement(locator).click();
  }

  public boolean isAlertPresent() {
    Alert alert = new WebDriverWait(driver, Duration.ofSeconds(20))
        .until(ExpectedConditions.alertIsPresent());
    if (alert == null) {
      return false;
    } else {
      driver.switchTo().alert();
      alert.accept();
      return true;
    }
  }

  public void pause(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public String takeScreenshot() {
    File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File screenshot = new File("screenshots/screen" + System.currentTimeMillis() + ".png");
    try {
      Files.copy(tmp, screenshot);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return screenshot.getAbsolutePath();
  }

}
