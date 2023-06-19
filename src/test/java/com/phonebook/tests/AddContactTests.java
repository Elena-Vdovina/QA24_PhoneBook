package com.phonebook.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (!isLoginLinkPresent()) {
      clickOnSignOutButton();
    }
    //click on Login link
    clickOnLoginLink();
    //enter email and password
    fillLoginRegistrationForm(new User().setEmail("qwe_rt@gmail.com").setPassword("Kross12345$"));
    //click on Login button
    clickOnLoginButton();

  }

  @Test
  public void addContactPositiveTest() {
    //click on ADD link
    clickOnAddLink();
    //fill in all fields
    fillAddContactForm(
        new Contact()
            .setName("Ivan")
            .setLastname("Ivanov")
            .setPhone("123456789987")
            .setEmail("Ivanov@gmail.com")
            .setAdress("Ivanovo")
            .setDesc("goalkeeper"));
    //click on Save button
    clickOnSaveButton();
    //assert contact
    Assert.assertTrue(isContactCreated("Ivan"));
  }

  @AfterMethod
  public void clearContact(){
    removeContact();
  }
}
