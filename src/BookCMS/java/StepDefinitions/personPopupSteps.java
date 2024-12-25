package StepDefinitions;

import com.beust.ah.A;
import configProject.CommonMethod;
import configProject.ConfigFileReader;
import configProject.WebDriverManager;
import gherkin.lexer.Th;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class personPopupSteps extends WebDriverManager {
    WebDriver driver = null;
    ConfigFileReader config = new ConfigFileReader();
    CommonMethod common = new CommonMethod(getDriver());

    // Info Login CMS
    private String email = config.getUserLogin();
    private String password = config.getPassLogin();

    // User
    private By Screen = By.xpath("//body");
    private By User = By.xpath("//span[@class='MuiBadge-root mis-2 mui-1rzb3uu']");

    // Popup person
    private By PopupPerson = By.className("shadow-lg");
    private By avatar = By.xpath("//div[@class='MuiAvatar-root MuiAvatar-circular MuiAvatar-colorDefault mui-1wc0c9p']");
    private By account = By.xpath("//div[@class='flex items-start flex-col']/span");
    private By ResetPass = By.className("mui-1ojoxkq");
    private By TextResetPass = By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 mui-5tntxm']");
    private By IconReset = By.xpath("//i[@class='tabler-settings text-[22px]']");
    private By BtnLogout = By.className("mui-1osfr61");
    private By IconLogout = By.xpath("//i[@class='tabler-logout']");

    // Popup Reset password
    private By PopupReset = By.className("w-[370px]");
    private By TitlePopup = By.xpath("//span[@class='text-textPrimary text-[24px] font-semibold leading-[22px]']");
    private By BtnX = By.xpath("//i[@class='tabler-x text-[16px]']");
    // Input Mật khẩu cũ
    private By TitleInput1 = By.xpath("//label[@id=':r7:-label']");
    private By Field1 = By.xpath("//form/div[1]/div");
    private By Input1 = By.xpath("//input[@name='password']");
    private By Hide1 = By.xpath("//form/div[1]/div/div/button");
    // Input Mật khẩu mới
    private By TitleInput2 = By.xpath("//label[@id=':r8:-label']");
    private By Field2 = By.xpath("//form/div[2]/div");
    private By Input2 = By.xpath("//input[@name='newPassword']");
    private By Hide2 = By.xpath("//form/div[2]/div/div/button");
    // Input Xác nhận mật khẩu
    private By TitleInput3 = By.xpath("//label[@id=':r9:-label']");
    private By Field3 = By.xpath("//form/div[3]/div");
    private By Input3 = By.xpath("//input[@name='confirm']");
    private By Hide3 = By.xpath("//form/div[3]/div/div/button");
    // Button Đổi mật khẩu
    private By ButtonResetPass = By.xpath("//button[@id=':ra:']");

    // Toast message
    private By Message = By.className("go3958317564");

    /**
     * Các hàm chung
     */
    @Given("Popup Person: Open popup")
    public void popup_person_open_popup() throws InterruptedException {
        // Mở trình duyệt
        driver = getDriver();
        driver.navigate().to(config.getApplicationUrl() + "login");
        common.waitForPageLoaded();
        MenuSteps.getInstance().ImplicitlyWait_Config();

        // Nhập thông tin login
        By Email = By.xpath("//input[@name='email']");
        By Pass = By.xpath("//input[@name='password']");
        common.sendKeyElement(Email, email);
        common.sendKeyElement(Pass, password);

        // Nhấn btn [Đăng nhập]
        By BtnLogin = By.xpath("//button[@id=':Rjtttquda:']");
        common.clickElement(BtnLogin);
        common.waitForPageLoaded();
        // Nhấn mở popup
        common.clickElement(User);

        Thread.sleep(1500);
        System.out.println("Thành công mở màn hình Sách");
    }

    @And("Popup Person: Reload page")
    public void popup_person_reload_page(){
        driver.navigate().refresh();
        common.waitForPageLoaded();
        System.out.println("Reload page thành công");
    }

    @And("Popup Person: Click background screen")
    public void popup_person_click_background_screen() throws InterruptedException {
        common.clickElement(Screen);
        Thread.sleep(1500);
        System.out.println("Pass");
    }

    @And("Popup Reset pass: Click popup")
    public void popup_reset_pass_click_popup(){
        personPopupSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(PopupReset);
        System.out.println("Pass");
    }

    @And("Popup Person: Close browser")
    public void popup_person_close_browser() throws InterruptedException {
        bookSteps.getInstance().CloseDriver();
    }


    /**
     * Giao diện popup
     */
    @When("Popup person UI")
    public void popup_person_ui(){
        // Ktra popup
        Boolean en_popup = common.checkDisplay(PopupPerson);
        Assert.assertEquals(en_popup, true);
        WebElement popup_ele = driver.findElement(PopupPerson);
        Assert.assertEquals(popup_ele.getCssValue("background-color"), "rgb(255, 255, 255)");
        System.out.println("Popup Pass");

        // User
        Boolean en_ava = common.checkDisplay(avatar);
        Assert.assertEquals(en_ava, true);
        Boolean en_acc = common.checkDisplay(account);
        Assert.assertEquals(en_acc, true);
        System.out.println("User info Pass");

        // Reset password
        Boolean en_resetPass = common.checkDisplay(ResetPass);
        Assert.assertEquals(en_resetPass, true);
        WebElement reset = driver.findElement(ResetPass);
        Assert.assertEquals(reset.getCssValue("cursor"), "pointer");
        System.out.println("Reset password Pass");

        // Button
        Boolean en_button= common.checkDisplay(BtnLogout);
        Assert.assertEquals(en_button, true);
        WebElement button = driver.findElement(BtnLogout);
        Assert.assertEquals(button.getCssValue("cursor"), "pointer");
        Assert.assertEquals(button.getCssValue("background-color"), "rgb(255, 76, 81)");
        System.out.println("Button logout Pass");
    }

    @Then("Popup person UI: User")
    public void popup_person_ui_user(){
        // Avatar mặc định
        WebElement person_icon = driver.findElement(avatar);
        Assert.assertEquals(person_icon.getCssValue("width"), "40px");
        Assert.assertEquals(person_icon.getCssValue("height"), "40px");
        Assert.assertEquals(person_icon.getCssValue("border-radius"), "50%");
        Assert.assertEquals(person_icon.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        Assert.assertEquals(person_icon.getCssValue("background-color"), "rgb(238, 237, 240)");
        System.out.println("Avatar Pass");

        // Account
        WebElement acc = driver.findElement(account);
        Assert.assertEquals(acc.getText(), email);
        Assert.assertEquals(acc.getCssValue("color"), "rgba(47, 43, 61, 0.4)");
        Assert.assertEquals(acc.getCssValue("font-size"), "13px");
        Assert.assertEquals(acc.getCssValue("font-weight"), "400");
        System.out.println("Account Pass");
    }

    @Then("Popup person UI: Reset password")
    public void popup_person_ui_reset_password(){
        // Icon
        WebElement icon_reset = driver.findElement(IconReset);
        Assert.assertEquals(icon_reset.getCssValue("font-size"), "22px");
        System.out.println("Icon reset Pass");

        // Text
        WebElement text = driver.findElement(TextResetPass);
        Assert.assertEquals(text.getText(), "Đổi mật khẩu");
        Assert.assertEquals(text.getCssValue("font-size"), "15px");
        Assert.assertEquals(text.getCssValue("font-weight"), "400");
        Assert.assertEquals(text.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        System.out.println("Text reset Pass");
    }

    @Then("Popup person UI: Button Logout")
    public void popup_person_ui_button_logout(){
        // Btn
        WebElement btn = driver.findElement(BtnLogout);
        Assert.assertEquals(btn.getText(), "Đăng xuất");
        Assert.assertEquals(btn.getCssValue("background-color"), "rgb(255, 76, 81)");
        Assert.assertEquals(btn.getCssValue("color"), "rgb(255, 255, 255)");
        System.out.println("Button Logout Pass");

        // Icon btn
        WebElement icon_btn = driver.findElement(IconLogout);
        Assert.assertEquals(icon_btn.getCssValue("font-size"), "14px");
        Assert.assertEquals(icon_btn.getCssValue("color"), "rgb(255, 255, 255)");
        System.out.println("Icon logout Pass");
    }


    /**
     * Action vào popup
     */
    @And("Popup person: Click Reset password")
    public void popup_person_click_reset_password() throws InterruptedException {
        common.clickElement(ResetPass);
        Thread.sleep(3000);
        System.out.println("Pass");
    }

    @And("Popup person: Click button Logout")
    public void popup_person_click_button_logout() throws InterruptedException {
        common.clickElement(BtnLogout);
        Thread.sleep(3000);
        System.out.println("Pass");
    }

    @And("Password current: Click input")
    public void password_current_click_input(){
        personPopupSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(Input1);
        System.out.println("Pass");
    }

    @When("Popup Reset pass: Enter {string} input password current")
    public void popup_reset_pass_enter_input_password_current(String p){
        personPopupSteps.getInstance().ImplicitlyWait_Config();
        common.sendKeyElement(Input1, p);
        System.out.println("Pass");
    }

    @And("Password current: Click icon hide")
    public void password_current_click_icon_hide(){
        personPopupSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(Hide1);
        System.out.println("Pass");
    }

    @And("Password new: Click input")
    public void password_new_click_input(){
        personPopupSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(Input2);
        System.out.println("Pass");
    }

    @When("Popup Reset pass: Enter {string} input password new")
    public void popup_reset_pass_enter_input_password_new(String p){
        personPopupSteps.getInstance().ImplicitlyWait_Config();
        common.sendKeyElement(Input2, p);
        System.out.println("Pass");
    }

    @And("Password new: Click icon hide")
    public void password_new_click_icon_hide(){
        personPopupSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(Hide2);
        System.out.println("Pass");
    }

    @And("Confirm pass: Click input")
    public void confirm_pass_click_input(){
        personPopupSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(Input3);
        System.out.println("Pass");
    }

    @When("Popup Reset pass: Enter {string} input confirm")
    public void popup_reset_pass_enter_input_confirm(String p){
        personPopupSteps.getInstance().ImplicitlyWait_Config();
        common.sendKeyElement(Input3, p);
        System.out.println("Pass");
    }

    @And("Confirm pass: Click icon hide")
    public void confirm_pass_click_icon_hide(){
        personPopupSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(Hide3);
        System.out.println("Pass");
    }

    @And("Popup Reset pass: Click button Reset pass")
    public void popup_reset_pass_click_button_reset_pass(){
        personPopupSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(ButtonResetPass);
        System.out.println("Pass");
    }


    /**
     * Các hàm, màn hình sau khi thực hiện action
     */
    @Then("Logout successful")
    public void logout_successful(){
        Assert.assertEquals(driver.getCurrentUrl(), "https://book-cms.vmgmedia.vn/login");
        System.out.println("Logout thành công");
    }

    @Then("Close popup person")
    public void close_popup_person(){
        Boolean en_popup = common.checkDisplay(PopupPerson);
        Assert.assertEquals(en_popup, false);
    }

    @Then("Popup Reset password UI")
    public void popup_reset_password_ui(){
        // Mở popup
        Boolean en_popup = common.checkDisplay(PopupReset);
        Assert.assertEquals(en_popup, true);

        // Btn X
        Boolean en_X = common.checkDisplay(BtnX);
        Assert.assertEquals(en_X, true);

        // Input Mật khẩu hiện tại
        Boolean en_input1 = common.checkDisplay(Input1);
        Assert.assertEquals(en_input1, true);

        // Input Mật khẩu mới
        Boolean en_input2 = common.checkDisplay(Input2);
        Assert.assertEquals(en_input2, true);

        // Input Xác nhận mật khẩu
        Boolean en_input3 = common.checkDisplay(Input3);
        Assert.assertEquals(en_input3, true);

        // Btn
        Boolean en_btn = common.checkDisplay(ButtonResetPass);
        Assert.assertEquals(en_btn, true);
    }

    @Then("Popup Reset password detail UI")
    public void popup_reset_password_detail_ui(){
        // Mở popup
        WebElement popupTitle = driver.findElement(TitlePopup);
        Assert.assertEquals(popupTitle.getText(), "Đổi mật khẩu");
        Assert.assertEquals(popupTitle.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        Assert.assertEquals(popupTitle.getCssValue("font-weight"), "600");
        Assert.assertEquals(popupTitle.getCssValue("font-size"), "24px");

        // Button X
        WebElement x = driver.findElement(BtnX);
        Assert.assertEquals(x.getCssValue("color"), "rgba(47, 43, 61, 0.6)");
        Assert.assertEquals(x.getCssValue("font-size"), "16px");

        // Mật khẩu hiện tại
        WebElement title_pass_cur = driver.findElement(TitleInput1);
        Assert.assertEquals(title_pass_cur.getText(), "Mật khẩu hiện tại");
        Assert.assertEquals(title_pass_cur.getCssValue("font-size"), "13px");
        Assert.assertEquals(title_pass_cur.getCssValue("font-weight"), "400");
        Assert.assertEquals(title_pass_cur.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        WebElement input_pass_cur = driver.findElement(Input1);
        Assert.assertEquals(input_pass_cur.getAttribute("type"), "password");
        Assert.assertEquals(input_pass_cur.getAttribute("placeholder"), "············");
        Assert.assertEquals(input_pass_cur.getCssValue("font-size"), "15px");
        Assert.assertEquals(input_pass_cur.getCssValue("font-weight"), "400");
        Assert.assertEquals(input_pass_cur.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        WebElement hide_pass_cur = driver.findElement(Hide1);
        Assert.assertEquals(hide_pass_cur.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        Assert.assertEquals(hide_pass_cur.getCssValue("background-color"), "rgba(0, 0, 0, 0)");
        Assert.assertEquals(hide_pass_cur.getCssValue("font-size"), "24px");

        // Mật khẩu mới
        WebElement title_pass_new = driver.findElement(TitleInput2);
        Assert.assertEquals(title_pass_new.getText(), "Mật khẩu mới");
        Assert.assertEquals(title_pass_new.getCssValue("font-size"), "13px");
        Assert.assertEquals(title_pass_new.getCssValue("font-weight"), "400");
        Assert.assertEquals(title_pass_new.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        WebElement input_pass_new = driver.findElement(Input2);
        Assert.assertEquals(input_pass_new.getAttribute("type"), "password");
        Assert.assertEquals(input_pass_new.getAttribute("placeholder"), "············");
        Assert.assertEquals(input_pass_new.getCssValue("font-size"), "15px");
        Assert.assertEquals(input_pass_new.getCssValue("font-weight"), "400");
        Assert.assertEquals(input_pass_new.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        WebElement hide_pass_new = driver.findElement(Hide2);
        Assert.assertEquals(hide_pass_new.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        Assert.assertEquals(hide_pass_new.getCssValue("background-color"), "rgba(0, 0, 0, 0)");
        Assert.assertEquals(hide_pass_new.getCssValue("font-size"), "24px");

        // Xác nhận mật khẩu
        WebElement title_confirm = driver.findElement(TitleInput3);
        Assert.assertEquals(title_confirm.getText(), "Xác nhận mật khẩu");
        Assert.assertEquals(title_confirm.getCssValue("font-size"), "13px");
        Assert.assertEquals(title_confirm.getCssValue("font-weight"), "400");
        Assert.assertEquals(title_confirm.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        WebElement input_confirm = driver.findElement(Input3);
        Assert.assertEquals(input_confirm.getAttribute("type"), "password");
        Assert.assertEquals(input_confirm.getAttribute("placeholder"), "············");
        Assert.assertEquals(input_confirm.getCssValue("font-size"), "15px");
        Assert.assertEquals(input_confirm.getCssValue("font-weight"), "400");
        Assert.assertEquals(input_confirm.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        WebElement hide_confirm = driver.findElement(Hide3);
        Assert.assertEquals(hide_confirm.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        Assert.assertEquals(hide_confirm.getCssValue("background-color"), "rgba(0, 0, 0, 0)");
        Assert.assertEquals(hide_confirm.getCssValue("font-size"), "24px");

        // Btton Đổi mật khẩu
        WebElement btn_reset = driver.findElement(ButtonResetPass);
        Assert.assertEquals(btn_reset.getText(), "Đổi mật khẩu");
        Assert.assertEquals(btn_reset.getCssValue("font-size"), "15px");
        Assert.assertEquals(btn_reset.getCssValue("font-weight"), "500");
        Assert.assertEquals(btn_reset.getCssValue("align-items"), "center");
        Assert.assertEquals(btn_reset.getCssValue("justify-content"), "center");
        Assert.assertEquals(btn_reset.getCssValue("cursor"), "pointer");
        Assert.assertEquals(btn_reset.getCssValue("color"), "rgb(255, 255, 255)");
        Assert.assertEquals(btn_reset.getCssValue("background-color"), "rgb(115, 103, 240)");
    }

    @Then("Password current: Check input")
    public void password_current_check_input(){
        // label
        WebElement title_pass_cur = driver.findElement(TitleInput1);
        Assert.assertEquals(title_pass_cur.getCssValue("color"), "rgb(115, 103, 240)");

        // border
        WebElement input_pass_cur = driver.findElement(Field1);
        Assert.assertEquals(input_pass_cur.getCssValue("border-color"), "rgb(115, 103, 240)");
    }

    @Then("Password new: Check input")
    public void password_new_check_input(){
        // label
        WebElement title_pass_new = driver.findElement(TitleInput2);
        Assert.assertEquals(title_pass_new.getCssValue("color"), "rgb(115, 103, 240)");

        // border
        WebElement input_pass_cur = driver.findElement(Field2);
        Assert.assertEquals(input_pass_cur.getCssValue("border-color"), "rgb(115, 103, 240)");
    }

    @Then("Confirm pass: Check input")
    public void confirm_pass_check_input(){
        // label
        WebElement title_confirm = driver.findElement(TitleInput3);
        Assert.assertEquals(title_confirm.getCssValue("color"), "rgb(115, 103, 240)");

        // border
        WebElement input_pass_cur = driver.findElement(Field3);
        Assert.assertEquals(input_pass_cur.getCssValue("border-color"), "rgb(115, 103, 240)");
    }

    @Then("Password current: Check entering {string} into input")
    public void password_current_check_entering_input(String p){
        popup_reset_pass_enter_input_password_current(p);
//        String s = common.Password(p, "·");

        // Kiểm tra sau khi nhập
        WebElement pass_cur = driver.findElement(Input1);
        Assert.assertEquals(pass_cur.getAttribute("type"), "password");
        Assert.assertEquals(common.getValue(Input1), p);
        Assert.assertEquals(pass_cur.getCssValue("font-size"), "15px");
        Assert.assertEquals(pass_cur.getCssValue("font-weight"), "400");
        Assert.assertEquals(pass_cur.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
    }

    @Then("Password current: Check input {string} when clicking hidden button")
    public void password_current_check_input_value_when_clicking_hidden_button(String p){
        popup_reset_pass_enter_input_password_current(p);

        // Kiểm tra sau khi nhập
        WebElement pass_cur = driver.findElement(Input1);
        Assert.assertEquals(pass_cur.getAttribute("type"), "text");
        Assert.assertEquals(common.getValue(Input1), p);
        Assert.assertEquals(pass_cur.getCssValue("font-size"), "15px");
        Assert.assertEquals(pass_cur.getCssValue("font-weight"), "400");
        Assert.assertEquals(pass_cur.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
    }

    @Then("Password new: Check entering {string} into input")
    public void password_new_check_entering_input(String p){
        popup_reset_pass_enter_input_password_new(p);
//        String s = common.Password(p, "·");

        // Kiểm tra sau khi nhập
        WebElement pass_new = driver.findElement(Input2);
        Assert.assertEquals(pass_new.getAttribute("type"), "password");
        Assert.assertEquals(common.getValue(Input2), p);
        Assert.assertEquals(pass_new.getCssValue("font-size"), "15px");
        Assert.assertEquals(pass_new.getCssValue("font-weight"), "400");
        Assert.assertEquals(pass_new.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
    }

    @Then("Password new: Check input {string} when clicking hidden button")
    public void password_new_check_input_value_when_clicking_hidden_button(String p){
        popup_reset_pass_enter_input_password_new(p);

        // Kiểm tra sau khi nhập
        WebElement pass_new = driver.findElement(Input2);
        Assert.assertEquals(pass_new.getAttribute("type"), "text");
        Assert.assertEquals(common.getValue(Input2), p);
        Assert.assertEquals(pass_new.getCssValue("font-size"), "15px");
        Assert.assertEquals(pass_new.getCssValue("font-weight"), "400");
        Assert.assertEquals(pass_new.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
    }

    @Then("Confirm pass: Check entering {string} into input")
    public void confirm_pass_check_entering_input(String p){
        popup_reset_pass_enter_input_confirm(p);
//        String s = common.Password(p, "·");

        // Kiểm tra sau khi nhập
        WebElement confirm = driver.findElement(Input3);
        Assert.assertEquals(confirm.getAttribute("type"), "password");
        Assert.assertEquals(common.getValue(Input3), p);
        Assert.assertEquals(confirm.getCssValue("font-size"), "15px");
        Assert.assertEquals(confirm.getCssValue("font-weight"), "400");
        Assert.assertEquals(confirm.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
    }

    @Then("Confirm pass: Check input {string} when clicking hidden button")
    public void confirm_pass_check_input_value_when_clicking_hidden_button(String p){
        popup_reset_pass_enter_input_confirm(p);

        // Kiểm tra sau khi nhập
        WebElement confirm = driver.findElement(Input3);
        Assert.assertEquals(confirm.getAttribute("type"), "text");
        Assert.assertEquals(common.getValue(Input3), p);
        Assert.assertEquals(confirm.getCssValue("font-size"), "15px");
        Assert.assertEquals(confirm.getCssValue("font-weight"), "400");
        Assert.assertEquals(confirm.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
    }


    /**
     * Các trường hợp lỗi input
     */
    // Kiểm tra bắt buộc
    @Then("Password current: Check required input")
    public void password_current_check_required_input(){
        // label
        WebElement title_pass_cur = driver.findElement(TitleInput1);
        Assert.assertEquals(title_pass_cur.getCssValue("color"), "rgb(255, 76, 81)");

        // border input
        WebElement input_pass_cur = driver.findElement(Field1);
        Assert.assertEquals(input_pass_cur.getCssValue("border-color"), "rgb(255, 76, 81)");

        // message
        By mess = By.xpath("//form/div[1]/p");
        WebElement message = driver.findElement(mess);
        Assert.assertEquals(message.getText(), "Vui lòng nhập trường này");
        Assert.assertEquals(message.getCssValue("color"), "rgb(255, 76, 81)");
        Assert.assertEquals(message.getCssValue("font-weight"), "400");
        Assert.assertEquals(message.getCssValue("font-size"), "13px");
    }

    @Then("Password new: Check required input")
    public void password_new_check_required_input(){
        // label
        WebElement title_pass_new = driver.findElement(TitleInput2);
        Assert.assertEquals(title_pass_new.getCssValue("color"), "rgb(255, 76, 81)");

        // border input
        WebElement input_pass_new = driver.findElement(Field2);
        Assert.assertEquals(input_pass_new.getCssValue("border-color"), "rgb(255, 76, 81)");

        // message
        By mess = By.xpath("//form/div[2]/p");
        WebElement message = driver.findElement(mess);
        Assert.assertEquals(message.getText(), "Từ 8 - 16 ký tự\n" + "Bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt");
        Assert.assertEquals(message.getCssValue("color"), "rgb(255, 76, 81)");
        Assert.assertEquals(message.getCssValue("font-weight"), "400");
        Assert.assertEquals(message.getCssValue("font-size"), "13px");
    }

    @Then("Confirm pass: Check required input")
    public void confirm_pass_check_required_input(){
        // label
        WebElement title_confirm = driver.findElement(TitleInput3);
        Assert.assertEquals(title_confirm.getCssValue("color"), "rgb(255, 76, 81)");

        // border input
        WebElement input_confirm = driver.findElement(Field3);
        Assert.assertEquals(input_confirm.getCssValue("border-color"), "rgb(255, 76, 81)");

        // message
        By mess = By.xpath("//form/div[3]/p");
        WebElement message = driver.findElement(mess);
        Assert.assertEquals(message.getText(), "Xác nhận mật khẩu không trùng khớp!");
        Assert.assertEquals(message.getCssValue("color"), "rgb(255, 76, 81)");
        Assert.assertEquals(message.getCssValue("font-weight"), "400");
        Assert.assertEquals(message.getCssValue("font-size"), "13px");
    }

    // Toast/flash message
    @Then("Password current: Incorrect password")
    public void password_current_incorrect_password(){
        String mess = common.messageText(Message);
        Assert.assertEquals(mess, "Mật khẩu không chính xác.");
        System.out.println("Pass");
    }

    /**
     * Thực hiện chức năng đổi mật khẩu thành công
     */
    @Then("Popup Reset pass: function performed successfully")
    public void popup_reset_pass_function_performed_successfully(){
        String mess = common.messageText(Message);
        Assert.assertEquals(mess, "Đổi mật khẩu thành công");
        System.out.println("Pass");
    }
}
