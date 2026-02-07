package tests.uiTests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

public class CreateUserScreen extends BaseTest {


    @Test(description = "Test the creation of a new user")
    public void test01_CreateAnewUser() {
        page.navigate("https://qa-company5-console.purpleray.com/login");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email")).fill("harel.elazar@scanovate.com");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email")).press("Tab");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).press("CapsLock");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).fill("H");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).press("CapsLock");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).fill("He123456");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).press("Enter");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("AUTORIZATION MANAGER")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("icon Users")).click();
        page.getByRole(AriaRole.BUTTON).filter(new Locator.FilterOptions().setHasText(Pattern.compile("^$"))).first().click();
        page.getByRole(AriaRole.TEXTBOX).first().click();
        page.getByRole(AriaRole.TEXTBOX).first().fill("sdfsdf");
        page.getByRole(AriaRole.TEXTBOX).nth(1).click();
        page.getByRole(AriaRole.TEXTBOX).first().fill("sdfsdff");
        page.getByRole(AriaRole.TEXTBOX).nth(1).fill("dsfsd");
        page.getByRole(AriaRole.TEXTBOX).nth(3).click();
        page.getByRole(AriaRole.TEXTBOX).nth(3).fill("test123@gmail.com");
        page.locator("div").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Select\\.\\.\\.$"))).nth(4).click();
        page.getByText("ConsoleService", new Page.GetByTextOptions().setExact(true)).click();
    }


}
