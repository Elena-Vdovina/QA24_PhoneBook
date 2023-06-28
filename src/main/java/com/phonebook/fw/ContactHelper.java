package com.phonebook.fw;

import com.phonebook.fw.BaseHelper;
import com.phonebook.model.Contact;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver driver) {
    super(driver);
  }

  public void clickOnSaveButton() {
    click(By.cssSelector(".add_form__2rsm2 button"));
  }

  public void fillAddContactForm(Contact contact) {
    //int i = (int) (System.currentTimeMillis()/1000)%3600;
    type(By.cssSelector("input:nth-child(1)"), contact.getName()); //"Ivan");  //"Ivan"+i
    type(By.cssSelector("input:nth-child(2)"), contact.getLastname()); //"Ivanov");
    type(By.cssSelector("input:nth-child(3)"), contact.getPhone()); //"123456789987");
    type(By.cssSelector("input:nth-child(4)"),
        contact.getEmail()); //"Ivanov@gmail.com"); //"Ivanov + i @gmail.com"
    type(By.cssSelector("input:nth-child(5)"), contact.getAdress()); //"Ivanovo");
    type(By.cssSelector("input:nth-child(6)"), contact.getDesc()); //"goalkeeper");
  }

  public void clickOnAddLink() {
    click(By.xpath("//a[.='ADD']"));
  }

  public boolean isContactCreated(String text) {
    List<WebElement> contacts = driver.findElements(By.cssSelector("h2"));
    for (WebElement element : contacts) {
      if (element.getText().contains(text)) {
        return true;
      }
    }
    return false;
  }

  public void removeContact() {
    if (!isContactListEmpty()) {
      click(By.tagName("h2"));
      click(By.xpath("//button[.='Remove']"));
    }
  }

  private boolean isContactListEmpty() {
    return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).isEmpty();
  }

  public int sizeOfContacts() {
    if (isElementPresent(By.cssSelector(".contact-item_card__2SOIM"))){
      return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
    }
    return 0;
  }

  public void closeAddForm(){
    click(By.xpath("//a[.='CONTACTS']"));
  }
}
