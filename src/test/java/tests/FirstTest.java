package tests;

import base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

public class FirstTest extends BaseTest {

    @Test
    public void verifyTitle() {
        page.navigate("https://google.com");
        System.out.println(page.title());

    }





}
