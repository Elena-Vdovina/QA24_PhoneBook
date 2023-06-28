package com.phonebook.tests;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
  //precondition: user should be logged out
  @BeforeMethod
  public void ensurePrecondition() {
    if (!app.getUser().isLoginLinkPresent()) {
      app.getUser().clickOnSignOutButton();
    }
  }

  @Test
  public void LoginPositiveTest() {
    //click on Login link
    app.getUser().clickOnLoginLink();
    //enter email and password
    app.getUser().fillLoginRegistrationForm(new User()
        .setEmail("qwe_rt@gmail.com")
        .setPassword("Kross12345$"));
    //click on Registration button
    app.getUser().clickOnLoginButton();
    //assert Sign out button is displayed
    Assert.assertTrue(app.getUser().isSignOutButtonPresent());
  }

  @Test
  public void LoginNegativeWithoutPasswordTest() {
    //click on Login link
    app.getUser().clickOnLoginLink();
    //enter only email
    app.getUser().fillLoginRegistrationForm(new User()
        .setEmail("qwe_rt@gmail.com"));
    //click on Registration button
    app.getUser().clickOnLoginButton();
    //assert Sign out button is displayed
    Assert.assertTrue(app.getUser().isAlertPresent());
  }

}
