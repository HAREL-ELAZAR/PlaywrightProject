package tests.uiTests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

public class FirstTest extends BaseTest {

//    @Test
//    public void verifyTitle() {
//        page.navigate("https://google.com");
//        System.out.println(page.title());
//
//    }


    @Test
    public void verifyTitle() {
        page.navigate("https://qa-company5-console.purpleray.com/login");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email")).fill("harel.elazar@scanovate.com");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email")).press("Tab");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).press("CapsLock");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).fill("H");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).press("CapsLock");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).fill("He123456");
        page.getByRole(AriaRole.BUTTON).filter(new Locator.FilterOptions().setHasText(Pattern.compile("^$"))).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in →")).click();
        page.getByText("Back to B-Trust as a user").click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Entities")).click();
        page.getByRole(AriaRole.BUTTON).filter(new Locator.FilterOptions().setHasText(Pattern.compile("^$"))).nth(1).click();
        page.getByText("Create entity and workflow").click();
        page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Select$"))).nth(3).click();
        page.getByText("Organization").click();
        page.getByText("Organization").click();
        page.getByText("Organization").click();
        page.navigate("https://qa-company5-console.purpleray.com/login");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email")).fill("harel.elazar@scanovate.com");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email")).press("Tab");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).press("CapsLock");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).fill("H");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).press("CapsLock");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).fill("He123456");
        page.getByRole(AriaRole.BUTTON).filter(new Locator.FilterOptions().setHasText(Pattern.compile("^$"))).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in →")).click();
        page.getByText("Back to B-Trust as a user").click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Entities")).click();
        page.getByRole(AriaRole.BUTTON).filter(new Locator.FilterOptions().setHasText(Pattern.compile("^$"))).nth(1).click();
        page.getByText("Create entity and workflow").click();
        page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Select$"))).nth(3).click();
        page.getByText("Organization").click();
        page.getByText("Organization").click();
        page.getByText("Organization").click();


    }
}
