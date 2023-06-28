package com.phonebook.tests;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

  //precondition: user should be logged out
  @BeforeMethod
  public void ensurePrecondition() {
    if (!app.getUser().isLoginLinkPresent()) {
      app.getUser().clickOnSignOutButton();
    }
  }

  @Test
  public void existedUserRegistrationNegativeTest() {
    //click on Login link
    app.getUser().clickOnLoginLink();
    //enter email and password
    app.getUser().fillLoginRegistrationForm(new User()
        .setEmail("qwe_rt@gmail.com")
        .setPassword("Kross12345$"));
    //click on Registration button
    app.getUser().clickOnRegistrationButton();
    //assert warning appears
    Assert.assertTrue(app.getUser().isAlertPresent());
  }

}
