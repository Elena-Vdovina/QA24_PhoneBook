package com.phonebook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTest extends TestBase{

  @BeforeMethod
  public void ensurePrecondition() {
    if (!app.getUser().isLoginLinkPresent()) {
      app.getUser().clickOnSignOutButton();
    }
    //click on Login link
    app.getUser().clickOnLoginLink();
    //enter email and password
    app.getUser().fillLoginRegistrationForm(new User().setEmail("qwe_rt@gmail.com").setPassword("Kross12345$"));
    //click on Login button
    app.getUser().clickOnLoginButton();
    //click on ADD link
    app.getContact().clickOnAddLink();
    //fill in all fields
    app.getContact().fillAddContactForm(
        new Contact()
            .setName("Ivan")
            .setLastname("Ivanov")
            .setPhone("123456789987")
            .setEmail("Ivanov@gmail.com")
            .setAdress("Ivanovo")
            .setDesc("goalkeeper"));
    //click on Save button
    app.getContact().clickOnSaveButton();
  }

  @Test
  public void removeContactTest(){
    app.getContact().removeContact();
    Assert.assertFalse(app.getContact().isContactCreated("Ivan"));
  }

}
