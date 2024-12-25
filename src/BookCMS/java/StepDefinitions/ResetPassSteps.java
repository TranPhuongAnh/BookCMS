package StepDefinitions;

import configProject.CommonMethod;
import configProject.ConfigFileReader;
import configProject.WebDriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class ResetPassSteps extends WebDriverManager {
    WebDriver driver = null;
    CommonMethod common = new CommonMethod(getDriver());
    ConfigFileReader config = new ConfigFileReader();


    /**
     * Khai báo các element
     */
    private By Screen = By.xpath("//body");
    private By ImgTop = By.xpath("//img[@class ='absolute top-[48px] left-[-45px] z-[-1]']");
    private By ImgBot = By.xpath("//img[@class ='absolute bottom-[32px] right-[-56px] z-[-1]']");
    private By ImgLogo = By.xpath("//img[@alt='login-img']");
    private By Pass = By.name("password");
    private By TitlePass = By.xpath("//form//div[1]//label");
    private By ConfirmPass = By.name("confirm");
    private By TitleConfirmPass = By.xpath("//form//div[2]//label");
    private By LoginBack = By.className("mui-tvg662");
    private By BtnReset = By.xpath("//button[@id=':Rftttquda:']");
    private By Message = By.className("go3958317564");

    /**
     * Các hàm chung
     */
    @Given("Open reset password page with token")
    public void open_reset_password_page_with_token(){
        driver = getDriver();
        driver.navigate().to(config.getApplicationUrl() + "change-password?token=" + config.getToken());
        common.waitForPageLoaded();
        System.out.println("Pass");
    }

    @And("Reset pass: Click background screen")
    public void reset_pass_click_background_screen(){
        loginSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(Screen);
        System.out.println("Pass");
    }

    @And("Reset pass: Close browser")
    public void reset_pass_close_browser() throws InterruptedException {
        loginSteps.getInstance().CloseDriver();
    }

    /**
     * Giao diện Reset password page
     */
    @When("Reset password page title")
    public void reset_password_page_title() {
        String titleCurr = driver.getTitle();
        Assert.assertEquals(titleCurr, "EasyVoix - Lắng nghe trí tuệ");

        WebElement iconImg = driver.findElement(By.xpath("//link[@type='image/x-icon']"));
        Assert.assertEquals(iconImg.getAttribute("href"), "https://book-cms.vmgmedia.vn/favicon.ico");
        System.out.println("Pass");
    }

    @When("Reset password page UI")
    public void reset_password_page_ui(){
        //Img top dưới form
        Boolean en_img_top = common.checkDisplay(ImgTop);
        Assert.assertEquals(en_img_top, true);
        //Img bottom dưới form
        Boolean en_img_bot = common.checkDisplay(ImgBot);
        Assert.assertEquals(en_img_bot, true);
        //check display img logo
        Boolean en_logo = common.checkDisplay(ImgLogo);
        Assert.assertEquals(en_logo, true);
        //check display input pass
        Boolean en_pass = common.checkDisplay(Pass);
        Assert.assertEquals(en_pass, true);
        Boolean en_confirm_pass = common.checkDisplay(ConfirmPass);
        Assert.assertEquals(en_confirm_pass, true);
        //check display link text forgot pass
        Boolean en_login_back = common.checkDisplay(LoginBack);
        Assert.assertEquals(en_login_back, true);
        //check display btn Login
        Boolean en_btn_reset = common.checkDisplay(BtnReset);
        Assert.assertEquals(en_btn_reset, true);

        System.out.println("Pass");
    }

    @When("Reset password logo")
    public void reset_password_logo() {
        WebElement logo = driver.findElement(ImgLogo);
        //src img
        Assert.assertEquals(logo.getAttribute("src"), "https://book-cms.vmgmedia.vn/images/svgs/forgot-img.svg");
        System.out.println("Pass");
    }

    @When("Reset password content")
    public void reset_password_content(){
        //content
        WebElement hi = driver.findElement(By.xpath("//h4"));
        Assert.assertEquals(hi.getText(), "Đặt lại mật khẩu \uD83D\uDD12");

        WebElement content = driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 text-textSecondary mui-h8wx22']"));
        Assert.assertEquals(content.getText(), "Mật khẩu mới của bạn phải khác với mật khẩu đã sử dụng trước đó");

        System.out.println("Pass");
    }

    /**
     * Kiểm tra trường password
     */
    @When("Reset password: Default password input")
    public void reset_password_default_password_input(){
        WebElement title_p = driver.findElement(TitlePass);
        WebElement pass = driver.findElement(Pass);
        WebElement border_input = driver.findElement(By.className("mui-5we34v"));

        //Title input
        Assert.assertEquals(title_p.getText(), "Mật khẩu mới");
        //type
        Assert.assertEquals(pass.getAttribute("type"), "password");
        //placeholder
        Assert.assertEquals(pass.getAttribute("placeholder"), "············");
        //Giá trị mặc định
        Assert.assertEquals(pass.getText(), "");
        //Color
        String color_title = title_p.getCssValue("color");
        String color_border_input = border_input.getCssValue("border-color");
        Assert.assertEquals(color_title, "rgba(47, 43, 61, 0.9)");
        Assert.assertEquals(color_border_input, "rgba(47, 43, 61, 0.22)");

        System.out.println("Pass");
    }

    @When("Reset password: Enter password input value {string}")
    public void reset_password_enter_password_input_email(String p){
        loginSteps.getInstance().ImplicitlyWait_Config();
        common.sendKeyElement(Pass, p);
    }

    @Then("Reset password: Pass format error message")
    public void reset_password_pass_format_error_message(){
        WebElement mess = driver.findElement(By.xpath("//form//div[1]//p"));
        WebElement border_input = driver.findElement(By.xpath("//form//div[1]//div"));

        //mess content
        Assert.assertEquals(mess.getText(), "Từ 8 - 16 ký tự\n" + "Bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt");
        //color
        String color_mess = mess.getCssValue("color");
        String color_title = driver.findElement(TitlePass).getCssValue("color");
        String color_border_input = border_input.getCssValue("border-color");

        Assert.assertEquals(color_mess, "rgba(255, 76, 81, 1)");
        System.out.println("Pass");
//        Assert.assertEquals(color_title, "rgba(224, 71, 78, 0.984)");
//        System.out.println("Pass");
        Assert.assertEquals(color_border_input, "rgb(255, 76, 81)");
        System.out.println("Pass");
    }

    /**
     * Kiểm tra trường Confirm Password
     */
    @When("Reset password: Default confirm password input")
    public void reset_password_default_confirm_password_input(){
        WebElement title_p = driver.findElement(TitleConfirmPass);
        WebElement pass = driver.findElement(ConfirmPass);
        WebElement border_input = driver.findElement(By.className("mui-5we34v"));

        //Title input
        Assert.assertEquals(title_p.getText(), "Xác nhận mật khẩu");
        //type
        Assert.assertEquals(pass.getAttribute("type"), "password");
        //placeholder
        Assert.assertEquals(pass.getAttribute("placeholder"), "············");
        //Giá trị mặc định
        Assert.assertEquals(pass.getText(), "");
        //Color
        String color_title = title_p.getCssValue("color");
        String color_border_input = border_input.getCssValue("border-color");
        Assert.assertEquals(color_title, "rgba(47, 43, 61, 0.9)");
        Assert.assertEquals(color_border_input, "rgba(47, 43, 61, 0.22)");

        System.out.println("Pass");
    }

    @When("Reset password: Enter confirm password input value {string}")
    public void reset_password_enter_confirm_password_input_email(String p){
        loginSteps.getInstance().ImplicitlyWait_Config();
        common.sendKeyElement(ConfirmPass, p);
    }

    @Then("Reset password: Confirm pass format error message")
    public void reset_password_confirm_pass_format_error_message(){
        WebElement mess = driver.findElement(By.xpath("//form//div[2]//p"));
        WebElement border_input = driver.findElement(By.xpath("//form//div[2]//div"));

        //mess content
        Assert.assertEquals(mess.getText(), "Xác nhận mật khẩu không trùng khớp!");
        //color
        String color_mess = mess.getCssValue("color");
        String color_title = driver.findElement(TitleConfirmPass).getCssValue("color");
        String color_border_input = border_input.getCssValue("border-color");

        Assert.assertEquals(color_mess, "rgba(255, 76, 81, 1)");
        System.out.println("Pass");
//        Assert.assertEquals(color_title, "rgba(224, 71, 78, 0.984)");
//        System.out.println("Pass");
        Assert.assertEquals(color_border_input, "rgb(255, 76, 81)");
        System.out.println("Pass");
    }

    /**
     * Giao diện & action button [Đặt mật khẩu mới]
     */
    @When("Default button Reset password")
    public void default_button_reset_password(){
        WebElement btnLogin = driver.findElement(BtnReset);

        //text
        Assert.assertEquals(btnLogin.getText(), "Đặt mật khẩu mới");
        //color
        String color = btnLogin.getCssValue("background-color");
        Assert.assertEquals(color, "rgba(115, 103, 240, 1)"); //hex #7367F0
        //type
        Assert.assertEquals(btnLogin.getAttribute("type"), "submit");
        //enable btn
        Boolean enable = common.checkEnable(BtnReset);
        Assert.assertEquals(enable, true);

        System.out.println("Pass");
    }

    @And("Click button Reset password")
    public void click_button_reset_password() {
        loginSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(BtnReset);
        common.waitForPageLoaded();
        System.out.println("Pass");
    }

    /**
     * Giao diện & action link text Quay lại đăng nhập
     */
    @When("Reset password: Default login back")
    public void reset_password_default_login_back(){
        WebElement forgotPass = driver.findElement(LoginBack);

        //text
        Assert.assertEquals(forgotPass.getText(), "Quay lại đăng nhập");
        //color
        String color = forgotPass.getCssValue("color");
        Assert.assertEquals(color, "rgba(115, 103, 240, 1)"); //hex #7367F0

        System.out.println("Pass");
    }

    @And("Reset password: Click login back")
    public void reset_password_click_login_back(){
        loginSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(LoginBack);
        System.out.println("Pass");
    }

    @Then("Reset password: Redirect to Login page")
    public void reset_password_redirect_to_login_page(){
        common.waitForPageLoaded();
        String redirectUrl = driver.getCurrentUrl();
        String redirectEx = "https://book-cms.vmgmedia.vn/login";
        Assert.assertEquals(redirectUrl, redirectEx);

        System.out.println("Pass");
    }

    /**
     * Thực hiện chức năng đổi mật khẩu thành công
     */
    @Then("Reset password successful")
    public void reset_pass_successful(){
        String redirectUrl = driver.getCurrentUrl();
        String redirectEx = "https://book-cms.vmgmedia.vn/login";
        Assert.assertEquals(redirectUrl, redirectEx);

        System.out.println("Pass");
    }
}
