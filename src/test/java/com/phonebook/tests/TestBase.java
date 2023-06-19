package com.phonebook.tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

  WebDriver driver;

  @BeforeMethod
  public void setUp(){
    System.err.close(); // скрывает системные настройки при выводе в консоль
    driver = new ChromeDriver();
    driver.get("https://telranedu.web.app");
    driver.manage().window().maximize();
    // wait for all elements on the site to load before starting test
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  }

  public boolean isElementPresent(By locator){
    return driver.findElements(locator).size()>0;

  }

  public boolean isHomeComponentPresent(){
    return isElementPresent(By.cssSelector("div:nth-child(2)>div>div"));

  }

  public boolean isHomeComponentPresent1(){
    try {
      driver.findElement(By.xpath("//h1[.='Home Component']"));
      return true;
    } catch (NoSuchElementException ex){
      return false;
    }
  }

  public boolean isElementPresent1(By locator){
    try {
      driver.findElement(locator);
      return true;
    } catch (NoSuchElementException ex){
      return false;
    }
  }

  @AfterMethod(enabled = false)
  public void tearDown(){
    driver.quit();
  }

  public void type(By locator, String text) {
    if (text!=null) {
      click(locator);
      driver.findElement(locator).clear();
      driver.findElement(locator).sendKeys(text);
    }
  }

  public void click(By locator){
    driver.findElement(locator).click();
  }

  public boolean isAlertPresent(){
    Alert alert = new WebDriverWait(driver,Duration.ofSeconds(20))
        .until(ExpectedConditions.alertIsPresent());
    if (alert==null){
      return false;
    } else{
      driver.switchTo().alert();
      alert.accept();
      return true;
    }
  }


  public void clickOnSignOutButton() {
    driver.findElement(By.xpath("//button[.='Sign Out']")).click();
  }

  public boolean isLoginLinkPresent() {
    return isElementPresent(By.cssSelector("[href='/login']"));
        //By.xpath("//a[.='LOGIN']");
  }

  public void clickOnRegistrationButton() {
    click(By.name("registration"));
  }

  public void fillLoginRegistrationForm(User user) {
    type(By.cssSelector("[placeholder='Email']"), user.getEmail()); //"qwe_rt@gmail.com"
    type(By.cssSelector("[placeholder='Password']"), user.getPassword()); //"Kross12345$"
  }

  public void clickOnLoginLink() {
    click(By.cssSelector("[href='/login']"));
  }

  public boolean isSignOutButtonPresent() {
    return isElementPresent1(By.xpath("//button[.='Sign Out']"));
  }

  public void clickOnLoginButton() {
    click(By.name("login"));
  }

  public void clickOnSaveButton() {
    click(By.cssSelector(".add_form__2rsm2 button"));
  }

  public void fillAddContactForm(Contact contact) {
    //int i = (int) (System.currentTimeMillis()/1000)%3600;
    type(By.cssSelector("input:nth-child(1)"), contact.getName()); //"Ivan");  //"Ivan"+i
    type(By.cssSelector("input:nth-child(2)"), contact.getLastname()); //"Ivanov");
    type(By.cssSelector("input:nth-child(3)"), contact.getPhone()); //"123456789987");
    type(By.cssSelector("input:nth-child(4)"), contact.getEmail()); //"Ivanov@gmail.com"); //"Ivanov + i @gmail.com"
    type(By.cssSelector("input:nth-child(5)"), contact.getAdress()); //"Ivanovo");
    type(By.cssSelector("input:nth-child(6)"), contact.getAdress()); //"goalkeeper");
  }

  public void clickOnAddLink() {
    click(By.xpath("//a[.='ADD']"));
  }

  public boolean isContactCreated(String text){
    List<WebElement> contacts = driver.findElements(By.cssSelector("h2"));
    for (WebElement element: contacts){
      if (element.getText().contains(text))
        return true;
    }
    return false;
  }

  public void removeContact(){
    click(By.tagName("h2"));
    click(By.xpath("//button[.='Remove']"));
  }
}
