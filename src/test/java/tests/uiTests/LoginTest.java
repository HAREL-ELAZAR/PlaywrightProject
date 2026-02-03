package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import jdk.jfr.Description;
import org.testng.SkipException;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends BaseTest {

//    @Test(enabled = false)
//    public void test01() {
//        page.navigate("https://qa-company5-console.purpleray.com/login");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email")).click();
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email")).fill("harel.elazar@scanovate.com");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).click();
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).press("CapsLock");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).fill("A");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).press("CapsLock");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).fill("Amitbiton20!");
//        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in →")).click();
//        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("WORKFLOW MANAGEMENT")).click();
//        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("icon Mobile Interaction")).click();
//        page.getByRole(AriaRole.BUTTON).filter(new Locator.FilterOptions().setHasText(Pattern.compile("^$"))).click();
//        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("OCR").setExact(true)).click();
//        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("OCR").setExact(true)).click();
//    }
//
//
//    @Test(enabled = false)
//    public void test02() {
//        page.navigate("https://qa-company5-console.purpleray.com/login");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email")).click();
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email")).fill("harel.elazar@scanovate.co");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email")).press("Tab");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email")).click();
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email")).fill("harel.elazar@scanovate.com");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email")).press("Tab");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).press("CapsLock");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).press("CapsLock");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).fill("a");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).press("CapsLock");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).fill("aMITBITON20!");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).press("Enter");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).click();
//        page.getByRole(AriaRole.BUTTON).filter(new Locator.FilterOptions().setHasText(Pattern.compile("^$"))).click();
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).click();
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).click();
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).fill("");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).press("CapsLock");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).fill("");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).press("CapsLock");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).fill("A");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).press("CapsLock");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).fill("Amitbiton20!");
//        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).press("Enter");
//        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("General")).click();
//        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("icon User Experience Settings")).click();
//        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Mobile Process Settings")).click();
//        page.getByText("Desktop / Mobile Access").click();
//        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Mobile-Only Access Require")).click();
//        page.getByRole(AriaRole.CHECKBOX).nth(2).check();
//        page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Mobile Process Settings$"))).click();
//        assertThat(page.locator("#root")).containsText("Mobile Process Settings");
//    }
//
//
//    @Test(description = "Test successful login and navigate to Entities tab")
//    public void test03() {
//        LoginPage loginPage = new LoginPage(page);
//        HomePage homepage = new HomePage(page);
//        page.navigate("https://qa-company5-console.purpleray.com/login");
//        loginPage.login("harel.elazar@scanovate.com", "Amitbiton20!");
//        page.getByText("Back to B-Trust as a user").click();
//        homepage.selectTab("Entities");
//    }


//    @Test(description = "Test skipping test for the report")
//    public void test04() {
//        test.skip("Skipping this test");
//        throw new SkipException("Skipping this test");
//    }

    @Test(description = "Test successful login and navigate to Entities tab")
    public void test05() {
        LoginPage loginPage = new LoginPage(page);
        HomePage homepage = new HomePage(page);
        page.navigate("https://qa-company5-console.purpleray.com/login");
        loginPage.login("harel.elazar@scanovate.com", "He1234567");
        page.getByText("Back to B-Trust as a user").click();
        homepage.selectTab("Entities");
    }
}
