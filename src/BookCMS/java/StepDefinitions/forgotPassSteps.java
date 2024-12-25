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

public class forgotPassSteps extends WebDriverManager {
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
    private By Email = By.xpath("//input[@name='email']");
    private By TitleEmail = By.className("mui-3s1tnf");
    private By LoginBack = By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 flex items-center justify-center mt-1 mui-tvg662']");
    private By BtnSend = By.xpath("//button");
    private By Message = By.className("go3958317564");

    /**
     * Các hàm chung
     */
    @Given("Open forgot password page")
    public void open_forgot_password_page(){
        driver = getDriver();
        driver.navigate().to(config.getApplicationUrl() + "forgot-password");
        common.waitForPageLoaded();
        System.out.println("Pass");
    }

    @And("Forgot pass: Click background screen")
    public void forgot_pass_click_background_screen(){
        loginSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(Screen);
        System.out.println("Pass");
    }
    @And("Forgot pass: Close browser")
    public void forgot_pass_close_browser() throws InterruptedException {
        loginSteps.getInstance().CloseDriver();
    }

    /**
     * Giao diện form Quên mật khẩu
     */
    @When("Forgot password page title")
    public void forgot_password_page_title() {
        String titleCurr = driver.getTitle();
        Assert.assertEquals(titleCurr, "EasyVoix - Lắng nghe trí tuệ");

        WebElement iconImg = driver.findElement(By.xpath("//link[@type='image/x-icon']"));
        Assert.assertEquals(iconImg.getAttribute("href"), "https://book-cms.vmgmedia.vn/favicon.ico");
        System.out.println("Pass");
    }

    @When("Forgot password page UI")
    public void forgot_password_page_ui(){
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
        //check display link text forgot pass
        Boolean en_login_back = common.checkDisplay(LoginBack);
        Assert.assertEquals(en_login_back, true);
        //check display btn Login
        Boolean en_btn_send = common.checkDisplay(BtnSend);
        Assert.assertEquals(en_btn_send, true);

        System.out.println("Pass");
    }

    @When("Forgot password logo")
    public void forgot_password_logo() {
        WebElement logo = driver.findElement(ImgLogo);
        //src img
        Assert.assertEquals(logo.getAttribute("src"), "https://book-cms.vmgmedia.vn/images/svgs/forgot-img.svg");
        System.out.println("Pass");
    }

    @When("Forgot password content")
    public void forgot_password_content(){
        //content
        WebElement hi = driver.findElement(By.xpath("//h4"));
        Assert.assertEquals(hi.getText(), "Quên mật khẩu \uD83D\uDD12");

        WebElement content = driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 text-textSecondary mui-h8wx22']"));
        Assert.assertEquals(content.getText(), "Nhập email của bạn và chúng tôi sẽ gửi cho bạn hướng dẫn để đặt lại mật khẩu");

        System.out.println("Pass");
    }

    /**
     * Kiểm tra trường Email: giống các hàm tại loginSteps
     */
    @When("Forgot pass: Default email input")
    public void forgot_pass_default_email_input() {
        WebElement title_e = driver.findElement(TitleEmail);
        WebElement email = driver.findElement(Email);
        WebElement border_input = driver.findElement(By.className("mui-1qbjeep"));

        //Title input
        Assert.assertEquals(title_e.getText(), "Email");
        //type
        Assert.assertEquals(email.getAttribute("type"), "text");
        //placeholder
        Assert.assertEquals(email.getAttribute("placeholder"), "Nhập email của bạn");
        //Giá trị mặc định
        Assert.assertEquals(email.getText(), "");
        //Color
        String color_title = title_e.getCssValue("color");
        String color_border_input = border_input.getCssValue("border-color");
//        Assert.assertEquals(color_title, "rgba(47, 43, 61, 0.9)");
        Assert.assertEquals(color_border_input, "rgba(47, 43, 61, 0.22)");

        System.out.println("Pass");
    }

    @Then("Forgot pass: Required error message under email input")
    public void forgot_pass_required_error_message_under_email_input(){
        WebElement mess = driver.findElement(By.className("mui-b01i45"));
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

    @When("Forgot pass: Enter email input value {string}")
    public void forgot_pass_enter_input_email(String e){
        loginSteps.getInstance().ImplicitlyWait_Config();
        common.sendKeyElement(Email, e);
        System.out.println("Pass");
    }

    @And("Forgot pass: Clear value email input")
    public void forgot_pass_clear_value_input_email(){
        loginSteps.getInstance().ImplicitlyWait_Config();
        common.clearTextElement(Email);
        System.out.println("Pass");
    }

    @Then("Forgot pass: Email format error message")
    public void forgot_pass_email_format_error_message(){
        WebElement mess = driver.findElement(By.className("mui-b01i45"));
        WebElement border_input = driver.findElement(By.className("mui-1qbjeep"));

        //mess content
        Assert.assertEquals(mess.getText(), "Địa chỉ email không hợp lệ!");
        //color
        String color_mess = mess.getCssValue("color");
        String color_title = driver.findElement(TitleEmail).getCssValue("color");
        String color_border_input = border_input.getCssValue("border-color");

        Assert.assertEquals(color_mess, "rgba(255, 76, 81, 1)");
        System.out.println("Pass");
//        Assert.assertEquals(color_title, "rgba(194, 66, 75, 0.97)");
//        System.out.println("Pass");
        Assert.assertEquals(color_border_input, "rgb(255, 76, 81)");
        System.out.println("Pass");
    }

    /**
     * Giao diện & action Quay lại đăng nhập
     */
    @When("Forgot pass: Default login back")
    public void forgot_pass_default_login_back(){
        WebElement forgotPass = driver.findElement(LoginBack);

        //text
        Assert.assertEquals(forgotPass.getText(), "Quay lại đăng nhập");
        //color
        String color = forgotPass.getCssValue("color");
        Assert.assertEquals(color, "rgba(115, 103, 240, 1)"); //hex #7367F0

        System.out.println("Pass");
    }

    @And("Forgot pass: Click login back")
    public void forgot_pass_click_login_back(){
        loginSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(LoginBack);
        System.out.println("Pass");
    }

    @Then("Forgot pass: Redirect to Login page")
    public void forgot_pass_redirect_to_login_page(){
        common.waitForPageLoaded();
        String redirectUrl = driver.getCurrentUrl();
        String redirectEx = "https://book-cms.vmgmedia.vn/login";
        Assert.assertEquals(redirectUrl, redirectEx);

        System.out.println("Pass");
    }

    /**
     * Giao diện & action btn [Gửi Liên kết khôi phục]
     */
    @When("Default button Send link")
    public void default_button_send_link(){
        WebElement btnLogin = driver.findElement(BtnSend);

        //text
        Assert.assertEquals(btnLogin.getText(), "Gửi Liên kết khôi phục");
        //color
        String color = btnLogin.getCssValue("background-color");
        Assert.assertEquals(color, "rgba(115, 103, 240, 1)"); //hex #7367F0
        //type
        Assert.assertEquals(btnLogin.getAttribute("type"), "submit");
        //enable btn
        Boolean enable = common.checkEnable(BtnSend);
        Assert.assertEquals(enable, true);

        System.out.println("Pass");
    }

    @And("Click button Send link")
    public void click_button_send_link() {
        loginSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(BtnSend);
        System.out.println("Pass");
    }

    /**
     * Toast/flash message thông báo khi thực hiện chức năng gửi liên kết đến email
     * Các thông báo giống với login steps:
     * 1. Message Email must be an email
     * 2. Message Account does not exist
     */
    @Then("Forgot pass: Message Email must be an email")
    public void forgot_pass_message_email_must_be_an_email() {
        String mess = common.messageText(Message);
        Assert.assertEquals(mess, "email must be an email");
        System.out.println("Pass");
    }

    @Then("Forgot pass: Message Account does not exist")
    public void forgot_pass_message_account_does_not_exist(){
        String mess = common.messageText(Message);
        Assert.assertEquals(mess, "Tài khoản không tồn tại.");
        System.out.println("Pass");
    }

    @Then("Send link successful")
    public void send_link_successful(){
        common.waitForPageLoaded();
        //Content
        WebElement hi = driver.findElement(By.xpath("//h4"));
        Assert.assertEquals(hi.getText(), "Hãy kiểm tra hòm thư của bạn ✉\uFE0F");

        WebElement content = driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 text-textSecondary mui-h8wx22']"));
        WebElement span_acc = driver.findElement(By.xpath("//span[@class='text-textPrimary font-medium']"));
        Assert.assertEquals(content.getText(), "Thư mời đặt lại mật khẩu được gửi tới địa chỉ email của bạn: " + span_acc.getText() + " Vui lòng theo liên kết trong email để tiếp tục.");

        WebElement no = driver.findElement(By.xpath("//div[@class='text-[15px] text-textSecondary flex items-center justify-center']"));
        Assert.assertEquals(no.getText(), "Không nhận được liên kết?   \n" + "Gửi lại");

//        WebElement send_back = driver.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 cursor-pointer mui-tvg662']"));
//        Assert.assertEquals(send_back.getText(), "Gửi lại");

        //check display & text btn Login back
        By LB_btn = By.className("mui-19l7ogu");
        Boolean en_btn_LB = common.checkDisplay(LB_btn);
        Assert.assertEquals(en_btn_LB, true);
        WebElement Back_btn = driver.findElement(LB_btn);
        Assert.assertEquals(Back_btn.getText(), "Quay lại đăng nhập");

        System.out.println("Pass");
    }

    @When("Click Send back")
    public void click_send_back(){
        forgotPassSteps.getInstance().ImplicitlyWait_Config();
        By send_back = By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 cursor-pointer mui-tvg662']");
        common.clickElement(send_back);
        System.out.println("Pass");
    }

    @Then("Result click Send back")
    public void result_click_send_back(){
        //Chưa có action nên tạm để trống
        System.out.println("Pass");
    }

}
