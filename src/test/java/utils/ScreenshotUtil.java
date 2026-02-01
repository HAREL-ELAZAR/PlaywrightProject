package utils;

import com.microsoft.playwright.Page;

import java.util.Base64;

public class ScreenshotUtil {

    public static String takeScreenshotAsBase64(Page page) {
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        return Base64.getEncoder().encodeToString(screenshot);
    }
}