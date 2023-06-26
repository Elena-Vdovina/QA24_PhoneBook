package com.phonebook.tests;

import com.phonebook.fw.DataProviders;
import com.phonebook.model.Contact;
import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    if (!app.getUser().isLoginLinkPresent()) {
      app.getUser().clickOnSignOutButton();
    }
    //click on Login link
    app.getUser().clickOnLoginLink();
    //enter email and password
    app.getUser().fillLoginRegistrationForm(
        new User().setEmail("qwe_rt@gmail.com").setPassword("Kross12345$"));
    //click on Login button
    app.getUser().clickOnLoginButton();
  }

  @Test
  public void addContactPositiveTest() {
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
    //assert contact
    Assert.assertTrue(app.getContact().isContactCreated("Ivan"));
  }

  @Test(dataProvider = "addContact", dataProviderClass = DataProviders.class)
  public void addContactPositiveFromDataProviderTest(String name, String lastname, String phone,
      String email, String adress, String desc) {
    //click on ADD link
    app.getContact().clickOnAddLink();
    //fill in all fields
    app.getContact().fillAddContactForm(
        new Contact()
            .setName(name)
            .setLastname(lastname)
            .setPhone(phone)
            .setEmail(email)
            .setAdress(adress)
            .setDesc(desc));
    //click on Save button
    app.getContact().clickOnSaveButton();
    //assert contact
    Assert.assertTrue(app.getContact().isContactCreated(name));
  }

  @Test(dataProvider = "addContactFromCsvFile", dataProviderClass = DataProviders.class)
  public void addContactPositiveFromCsvFileTest(Contact contact) {
    //click on ADD link
    app.getContact().clickOnAddLink();
    //fill in all fields
    app.getContact().fillAddContactForm(contact);
    //click on Save button
    app.getContact().clickOnSaveButton();
  }

  @Test(dataProvider = "addContactNegativeFromCsvFile", dataProviderClass = DataProviders.class)
  public void addContactNegativeFromCsvFileTest(Contact contact) {
    //click on ADD link
    app.getContact().clickOnAddLink();
    //fill in all fields
    app.getContact().fillAddContactForm(contact);
    //click on Save button
    app.getContact().clickOnSaveButton();
    Assert.assertTrue(app.getUser().isAlertPresent());
    app.getContact().closeAddForm();
  }

  @AfterMethod
  public void clearContact() {
    app.getContact().removeContact();
  }

}
