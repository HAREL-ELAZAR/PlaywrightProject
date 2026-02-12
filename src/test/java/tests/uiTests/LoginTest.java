package tests.uiTests;

import base.BaseTest;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends BaseTest {

    @Test(description = "Test successful login and navigate to Entities tab")
    public void test01() {
        LoginPage loginPage = new LoginPage(page);
        HomePage homepage = new HomePage(page);
        page.navigate("https://qa-company5-console.purpleray.com/login");
        loginPage.login("harel.elazar@scanovate.com", "He123456");
        page.getByText("Back to B-Trust as a user").click();
        homepage.selectTab("Entities");
        System.out.println("test new harel branch");
    }



    @Test(description = "Test successful login and navigate to Entities tab")
    public void test02() {
        LoginPage loginPage = new LoginPage(page);
        HomePage homepage = new HomePage(page);
        page.navigate("https://qa-company5-console.purpleray.com/login");
        loginPage.login("harel.elazar@scanovate.com", "He123456");
        page.getByText("Back to B-Trust as a user").click();
        homepage.selectTab("Entities");
        System.out.println("test new harel branch");
    }

    @Test
    public void test03() {
        page.navigate("https://www.saucedemo.com/");

    }
}




