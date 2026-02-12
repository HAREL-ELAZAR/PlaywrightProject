package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {


    private Page page;

//    private final String userNameTextBox="GetByRoleOptions().setName(\"email\"))";
//    private final String passwordTextBox="GetByRoleOptions().setName(\"password\"))";
//    private final String loginButton="GetByRoleOptions().setName(\"Sign in →\"))";



    public LoginPage(Page page) {
        this.page = page;
    }
    public void  login (String userName,String Password){
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("email")).fill(userName);
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("password")).fill(Password);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in →")).click();
    }



}
