package StepDefinitions;


import configProject.CommonMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import configProject.ConfigFileReader;
import configProject.WebDriverManager;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;


public class loginSteps extends WebDriverManager {
    WebDriver driver = null;
    ConfigFileReader config = new ConfigFileReader();
    CommonMethod common = new CommonMethod(getDriver());

    /**
     * Khai báo các element
     */
    private By Screen = By.xpath("//body");
    private By ImgTop = By.xpath("//img[@class ='absolute top-[-52px] left-[-45px] z-[-1]']");
    private By ImgBot = By.xpath("//img[@class ='absolute bottom-[-46px] right-[-46px] z-[-1]']");
    private By ImgLogo = By.xpath("//img[@alt='login-img']");
    private By Email = By.xpath("//input[@name='email']");
    private By TitleEmail = By.xpath("//label[@id=':R27tttquda:-label']");
    private By Pass = By.xpath("//input[@name='password']");
    private By TitlePass = By.xpath("//label[@id='outlined-adornment-password-label']");
    private By ForgotPass = By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 text-end mui-tvg662']");
    private By BtnLogin = By.xpath("//button[@id=':Rjtttquda:']");
    private By Message = By.className("go3958317564");

    /**
     * Các hàm chung
     */
    @Given("Open Chrome and login page")
    public void open_chrome_and_login_page() {
        driver = getDriver();
        driver.navigate().to(config.getApplicationUrl() + "login");
        common.waitForPageLoaded();
        System.out.println("Thành công mở trình duyệt");
    }

    @And("Login: Click background screen")
    public void login_click_background_screen(){
        loginSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(Screen);
        System.out.println("Pass");
    }
    @And("Login: Close browser")
    public void login_close_browser() throws InterruptedException {
        loginSteps.getInstance().CloseDriver();
    }

    /**
     * Giao diện form Login
     */
    @When("Login page title")
    public void login_page_title() {
        String titleCurr = driver.getTitle();
        Assert.assertEquals(titleCurr, "Login");

        WebElement iconImg = driver.findElement(By.xpath("//link[@type='image/x-icon']"));
        Assert.assertEquals(iconImg.getAttribute("href"), "https://book-cms.vmgmedia.vn/favicon.ico");
        System.out.println("Pass");
    }

    @When("Login form UI")
    public void login_form_ui() {
        //Img top dưới form
        Boolean en_img_top = common.checkDisplay(ImgTop);
        Assert.assertEquals(en_img_top, true);
        //Img bottom dưới form
        Boolean en_img_bot = common.checkDisplay(ImgBot);
        Assert.assertEquals(en_img_bot, true);
        //check display img logo
        Boolean en_logo = common.checkDisplay(ImgLogo);
        Assert.assertEquals(en_logo, true);
        //check display input email
        Boolean en_email = common.checkDisplay(Email);
        Assert.assertEquals(en_email, true);
        //check display input pass
        Boolean en_pass = common.checkDisplay(Pass);
        Assert.assertEquals(en_pass, true);
        //check display link text forgot pass
        Boolean en_forgot_pass = common.checkDisplay(ForgotPass);
        Assert.assertEquals(en_forgot_pass, true);
        //check display btn Login
        Boolean en_btn_login = common.checkDisplay(BtnLogin);
        Assert.assertEquals(en_btn_login, true);

        System.out.println("Pass");
    }

    @When("Login logo")
    public void login_logo() {
        WebElement logo = driver.findElement(ImgLogo);
        //src img
        Assert.assertEquals(logo.getAttribute("src"), "https://book-cms.vmgmedia.vn/images/svgs/login-img.svg");
        System.out.println("Pass");
    }

    @When("Login content text")
    public void login_content_text(){
        //content
        WebElement hi = driver.findElement(By.xpath("//h4"));
        Assert.assertEquals(hi.getText(), "Xin chào bạn! \uD83D\uDC4B\uD83C\uDFFB");

        WebElement content = driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 text-textSecondary mui-h8wx22']"));
        Assert.assertEquals(content.getText(), "Vui lòng đăng nhập vào tài khoản của bạn để truy cập hệ thống quản trị");

        System.out.println("Pass");
    }

    /**
     * Kiểm tra trường Email
     */
    @When("Login: Default email input")
    public void login_default_email_input() {
        WebElement title_e = driver.findElement(TitleEmail);
        WebElement email = driver.findElement(Email);
        WebElement border_input = driver.findElement(By.className("mui-1qbjeep"));

        //Title input
        Assert.assertEquals(title_e.getText(), "Email đăng nhập");
        //type
        Assert.assertEquals(email.getAttribute("type"), "text");
        //placeholder
        Assert.assertEquals(email.getAttribute("placeholder"), "Nhập email của bạn");
        //Giá trị mặc định
        Assert.assertEquals(email.getText(), "");
        //Color
        String color_title = title_e.getCssValue("color");
        String color_border_input = border_input.getCssValue("border-color");
        Assert.assertEquals(color_title, "rgba(47, 43, 61, 0.9)");
        Assert.assertEquals(color_border_input, "rgba(47, 43, 61, 0.22)");

        System.out.println("Pass");
    }

    @Then("Login: Required error message under email input")
    public void login_required_error_message_under_email_input(){
        loginSteps.getInstance().ImplicitlyWait_Config();
        WebElement mess = driver.findElement(By.xpath("//p[@id=':R27tttquda:-helper-text']"));
        WebElement border_input = driver.findElement(By.className("mui-1qbjeep"));

        //mess content
        Assert.assertEquals(mess.getText(), "Vui lòng nhập trường này");
        //color
        String color_mess = mess.getCssValue("color");
        String color_title = driver.findElement(TitleEmail).getCssValue("color");
        String color_border_input = border_input.getCssValue("border-color");

        Assert.assertEquals(color_mess, "rgba(255, 76, 81, 1)");
        System.out.println("Pass");
//        Assert.assertEquals(color_title, "rgba(211, 69, 77, 0.976)");
//        System.out.println("Pass");
        Assert.assertEquals(color_border_input, "rgb(255, 76, 81)");
        System.out.println("Pass");
    }

    @When("Login: Enter email input value {string}")
    public void login_enter_input_email(String e){
        loginSteps.getInstance().ImplicitlyWait_Config();
        common.sendKeyElement(Email, e);
        System.out.println("Pass");
    }

    @And("Login: Clear value email input")
    public void login_clear_value_input_email(){
        loginSteps.getInstance().ImplicitlyWait_Config();
        common.clearTextElement(Email);
        System.out.println("Pass");
    }

    @Then("Login: Email format error message")
    public void login_email_format_error_message(){
        WebElement mess = driver.findElement(By.xpath("//p[@id=':R27tttquda:-helper-text']"));
        WebElement border_input = driver.findElement(By.className("mui-1qbjeep"));

        //mess content
        Assert.assertEquals(mess.getText(), "Địa chỉ email không hợp lệ!");
        //color
        String color_mess = mess.getCssValue("color");
        String color_title = driver.findElement(TitleEmail).getCssValue("color");
        String color_border_input = border_input.getCssValue("border-color");

        Assert.assertEquals(color_mess, "rgba(255, 76, 81, 1)");
        System.out.println("Pass");
//        Assert.assertEquals(color_title, "rgba(224, 71, 78, 0.984)");
//        System.out.println("Pass");
        Assert.assertEquals(color_border_input, "rgb(255, 76, 81)");
        System.out.println("Pass");
    }

    /**
     * Kiểm tra trường Password
     */
    @When("Login: Default password input")
    public void login_default_password_input(){
        WebElement title_p = driver.findElement(TitlePass);
        WebElement pass = driver.findElement(Pass);
        WebElement border_input = driver.findElement(By.className("mui-5we34v"));

        //Title input
        Assert.assertEquals(title_p.getText(), "Password");
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

    @Then("Login: Required error message under password input")
    public void login_required_error_message_under_password_input(){
        WebElement mess = driver.findElement(By.xpath("//p[@id='outlined-adornment-password-helper-text']"));
        WebElement border_input = driver.findElement(By.className("mui-5we34v"));

        //mess content
        Assert.assertEquals(mess.getText(), "Vui lòng nhập trường này");
        //color
        String color_mess = mess.getCssValue("color");
        String color_title = driver.findElement(TitlePass).getCssValue("color");
        String color_border_input = border_input.getCssValue("border-color");

        Assert.assertEquals(color_mess, "rgba(255, 76, 81, 1)");
        System.out.println("Pass");
//        Assert.assertEquals(color_title, "rgba(194, 66, 75, 0.97)");
//        System.out.println("Pass");
        Assert.assertEquals(color_border_input, "rgb(255, 76, 81)");
        System.out.println("Pass");
    }

    @When("Login: Enter password input value {string}")
    public void login_password_input_email(String p){
        loginSteps.getInstance().ImplicitlyWait_Config();
        common.sendKeyElement(Pass, p);
    }

    /**
     * Giao diện & action Quên mật khẩu
     */
    @When("Default forgot password")
    public void default_forgot_password(){
        WebElement forgotPass = driver.findElement(ForgotPass);

        //text
        Assert.assertEquals(forgotPass.getText(), "Quên mật khẩu?");
        //color
        String color = forgotPass.getCssValue("color");
        Assert.assertEquals(color, "rgba(115, 103, 240, 1)"); //hex #7367F0

        System.out.println("Pass");
    }

    @And("Click forgot password")
    public void click_forgot_password(){
        loginSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(ForgotPass);
        System.out.println("Pass");
    }

    @Then("Redirect to Forgot password page")
    public void redirect_to_forgot_password_page(){
        common.waitForPageLoaded();
        String redirectUrl = driver.getCurrentUrl();
        String redirectEx = "https://book-cms.vmgmedia.vn/forgot-password";
        Assert.assertEquals(redirectUrl, redirectEx);

        System.out.println("Pass");
    }

    /**
     * Giao diện & action button [Đăng nhập]
     */
    @When("Default button Login")
    public void default_button_login(){
        WebElement btnLogin = driver.findElement(BtnLogin);

        //text
        Assert.assertEquals(btnLogin.getText(), "Đăng nhập");
        //color
        String color = btnLogin.getCssValue("background-color");
        Assert.assertEquals(color, "rgba(115, 103, 240, 1)"); //hex #7367F0
        //type
        Assert.assertEquals(btnLogin.getAttribute("type"), "submit");
        //enable btn
        Boolean enable = common.checkEnable(BtnLogin);
        Assert.assertEquals(enable, true);

        System.out.println("Pass");
    }

    @And("Click button Login")
    public void click_button_login() {
        loginSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(BtnLogin);
        System.out.println("Pass");
    }

    /**
     * Toast/flash message thông báo khi thực hiện chức năng đăng nhập
     */
    @Then("Login: Message Email must be an email")
    public void login_message_email_must_be_an_email() {
        String mess = common.messageText(Message);
        Assert.assertEquals(mess, "email must be an email");
        System.out.println("Pass");
    }

    @Then("Login: Message Account does not exist")
    public void login_message_account_does_not_exist(){
        String mess = common.messageText(Message);
        Assert.assertEquals(mess, "Tài khoản không tồn tại.");
        System.out.println("Pass");
    }

    @Then("Login: Message Wrong password")
    public void login_message_wrong_password(){
        String mess = common.messageText(Message);
        Assert.assertEquals(mess, "Thông tin đăng nhập không chính xác.");
        System.out.println("Pass");
    }

    @Then("Login successful")
    public void login_successful(){
        common.waitForPageLoaded();
        String redirectUrl = driver.getCurrentUrl();
        String redirectEx = "https://book-cms.vmgmedia.vn/dashboard";
        Assert.assertEquals(redirectUrl, redirectEx);

        System.out.println("Pass");
    }
}
