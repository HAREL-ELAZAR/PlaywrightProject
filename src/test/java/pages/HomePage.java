package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {

    private Page page;


    public HomePage(Page page) {
        this.page = page;
    }

    private final String tabSelector = ".Navigation-module__main-menu__18i8n >li .Navigation-module__nav-item__2qZD0 span";

    public void selectTab(String searchTabName) {
        Locator tabs = page.locator(tabSelector);
        int count = tabs.count();
        for (int i = 0; i < count; i++) {
            Locator tab = tabs.nth(i);
            String tabText = tab.textContent().trim();
            System.out.println("The tab text: " + tabText );
            if (tabText.equalsIgnoreCase(searchTabName)) {
                System.out.println("Clicking on: " + searchTabName);
                tab.click();
                return;  // יציאה מהפונקציה
            }

        }
        System.out.println("Tab not found: " + searchTabName);
    }

}
