package tests.uiTests;

import base.BaseTest;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {

    @Test
    public void verifyTitle() {
        page.navigate("https://google.com");
        System.out.println(page.title());

    }





}
