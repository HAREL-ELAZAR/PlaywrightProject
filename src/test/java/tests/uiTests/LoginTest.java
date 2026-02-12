package tests.uiTests;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.ConfigManager;

public class LoginTest extends BaseTest {


    @DataProvider(name = "users")
    public Object[][] users() {
        String rawUsers = ConfigManager.getRequired("ui.users");

        String[] pairs = rawUsers.split(",");
        Object[][] data = new Object[pairs.length][2];

        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i].trim();
            String[] creds = pair.split(":", 2); // split only once

            if (creds.length != 2) {
                throw new IllegalArgumentException("Bad ui.users entry: '" + pair + "'. Expected format email:password");
            }

            data[i][0] = creds[0].trim();
            data[i][1] = creds[1].trim();
        }

        return data;
    }



    @Test(dataProvider = "users", description = "Login and navigate to Entities tab")
    public void loginFlow(String user, String pass) {

        String url = ConfigManager.getRequired("ui.base.url") + "/login";
        getPage().navigate(url);

        LoginPage loginPage = new LoginPage(getPage());
        HomePage homePage = new HomePage(getPage());

        loginPage.login(user, pass);
        getPage().getByText("Back to B-Trust as a user").waitFor();
        getPage().getByText("Back to B-Trust as a user").click();

        homePage.selectTab("Entities");
    }

}
