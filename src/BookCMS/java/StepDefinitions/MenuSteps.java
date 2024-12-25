package StepDefinitions;

import configProject.CommonMethod;
import configProject.ConfigFileReader;
import configProject.WebDriverManager;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import javax.imageio.ImageIO;
import java.time.Duration;

public class MenuSteps extends WebDriverManager {
    WebDriver driver = null;
    ConfigFileReader config = new ConfigFileReader();
    CommonMethod common = new CommonMethod(getDriver());

    // Info Login CMS
    private String email = config.getUserLogin();
    private String password = config.getPassLogin();

    // Menu chung
    private By Screen = By.xpath ("//body");
    private By Menu = By.xpath("//aside");
    private By Logo = By.xpath("//a[@class='flex items-center']");
    private By CircleDot = By.xpath("//span[@role='button']");
    private By NavMenu = By.xpath("//nav");

    // Overview
    private By TongQuan = By.xpath("//a[@label='Tổng quan']");
    private By iconTQ = By.xpath("//i[@class='tabler-chart-pie']");

    // Book list
    private By DanhMucSach = By.xpath("//a[@label='Danh mục sách']");
    private By iconDMS = By.xpath("//i[@class='tabler-folders']");

    // Author
    private By TacGia = By.xpath("//a[@label='Tác giả']");
    private By iconTacGia = By.xpath("//i[@class='tabler-school']");

    // Book
    private By Sach = By.xpath("//a[@label='Sách']");
    private By iconSach = By.xpath("//i[@class='tabler-books']");

    // Comments/Reviews
    private By BinhLuan = By.xpath("//a[@label='Bình luận/Đánh giá']");
    private By iconComment = By.xpath("//i[@class='tabler-brand-hipchat']");

    // Transaction
    private By GiaoDich = By.xpath("//nav/ul/li[3]/ul/li[2]/a[@class='ts-menu-button']");
    private By iconTransaction = By.xpath("//i[@class='tabler-info-circle']");
    private By icon_right_trans = By.xpath("//nav/ul/li[3]/ul/li[2]/a/span[3]/span/i[@class='tabler-chevron-right']"); //icon mũi tên bên phải Giao dịch
    private By MuaSach = By.xpath("//a[@label='Mua sách']");
    private By MuaXu = By.xpath("//a[@label='Mua xu']");

    // In-App Purchases
    private By IAP = By.xpath("//a[@label='In-App Purchases']");
    private By iconIAP = By.xpath("//i[@class='tabler-hexagon']");

    // Customer accounts
    private By TaiKhoanKH = By.xpath("//a[@label='Tài khoản khách hàng']");
    private By iconAccCus = By.xpath("//i[@class='tabler-users']");

    // Administrator
    private By QTV = By.xpath("//nav/ul/li[4]/ul/li[3]/a[@class='ts-menu-button']");
    private By iconQTV = By.xpath("//i[@class='tabler-shield-lock']");
    private By icon_right_QTV = By.xpath("//nav/ul/li[4]/ul/li[3]/a/span[3]/span/i"); //icon mũi tên bên phải QTV
    private By TaiKhoanQTV = By.xpath("//a[@label='Tài khoản quản trị viên']");
    private By PhanQuyen = By.xpath("//a[@label='Phân quyền']");
    private By VaiTro = By.xpath("//a[@label='Vai trò']");

    // Notification
    private By ThongBao = By.xpath("//a[@label='Thông báo']");
    private By iconNoti = By.xpath("//i[@class='tabler-bell']");

    /**
     * Các hàm chung
     */
    @Given("Menu: Open Chrome and login page")
    public void menu_open_chrome_and_login_page() {
        // Mở trình duyệt
        driver = getDriver();
        driver.navigate().to(config.getApplicationUrl() + "login");
        common.waitForPageLoaded();
        MenuSteps.getInstance().ImplicitlyWait_Config();

        // Nhập thông tin login
        By Email = By.xpath("//input[@name='email']");
        By Pass = By.xpath("//input[@name='password']");
        common.sendKeyElement(Email, email);
        MenuSteps.getInstance().ImplicitlyWait_Config();
        common.sendKeyElement(Pass, password);
        MenuSteps.getInstance().ImplicitlyWait_Config();

        // Nhấn btn [Đăng nhập]
        By BtnLogin = By.xpath("//button[@id=':Rjtttquda:']");
        common.clickElement(BtnLogin);

        common.waitForPageLoaded();
        System.out.println("Thành công mở trình duyệt và đăng nhập");
    }

    @And("Menu: Reload page")
    public void menu_reload_page() throws InterruptedException {
        driver.navigate().refresh();
        common.waitForPageLoaded();
        Thread.sleep(1500);
        System.out.println("Reload page thành công");
    }

    @And("Menu: Click background screen")
    public void click_background_screen(){
        MenuSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(Screen);
        System.out.println("Pass");
    }

    @And("Menu: Close browser")
    public void close_browser() throws InterruptedException {
        MenuSteps.getInstance().CloseDriver();
    }


    /**
     * Giao diện menu mặc định
     */
    @When("Menu default UI")
    public void menu_default_ui(){
        //Ktra hiển thị menu navigation
        Boolean en_nav_menu = common.checkDisplay(NavMenu);
        Assert.assertEquals(en_nav_menu, true);

        //Ktra nội dung phân loại menu
        MenuSteps.getInstance().ImplicitlyWait_Config();
        WebElement content = driver.findElement(By.xpath("//nav/ul/li[2]/ul/li[1]/span"));
        Assert.assertEquals(content.getText(), "QUẢN LÝ NỘI DUNG");
        WebElement transaction = driver.findElement(By.xpath("//nav/ul/li[3]/ul/li[1]/span"));
        Assert.assertEquals(transaction.getText(), "GIAO DỊCH & IAP");
        WebElement setting = driver.findElement(By.xpath("//nav/ul/li[4]/ul/li[1]/span"));
        Assert.assertEquals(setting.getText(), "QUẢN LÝ & THIẾT LẬP HỆ THỐNG");

        System.out.println("Pass");
    }

    @When("Menu display circle dot")
    public void menu_display_circle_dot(){
        //Ktra chấm tròn ẩn hiện menu
        Boolean en_circle_dot = common.checkDisplay(CircleDot);
        Assert.assertEquals(en_circle_dot, true);
    }

    @When("Menu default width size")
    public void menu_default_width_size(){
        //Menu size
        WebElement menu = driver.findElement(Menu);
        Assert.assertEquals(menu.getCssValue("inline-size"),"260px");
    }

    @When("Menu mini width size")
    public void menu_mini_width_size(){
        //Menu size
        WebElement menu = driver.findElement(Menu);
        Assert.assertEquals(menu.getCssValue("inline-size"),"71px");
    }

    @When("Logo menu default")
    public void logo_menu(){
        //Ktra logo
        Boolean en_logo = common.checkDisplay(Logo);
        Assert.assertEquals(en_logo, true);

        WebElement logo = driver.findElement(Logo);
        Assert.assertEquals(logo.getAttribute("href"), "https://book-cms.vmgmedia.vn/");
        Assert.assertEquals(logo.getCssValue("width"), "190px");
        Assert.assertEquals(logo.getCssValue("height"), "30px");
        System.out.println("Pass");
    }

    @When("Menu image screen shot")
    public void menu_image_screen_shot(){
        common.takeSnapShot("src/BookCMS/Image/Actual/menu_screen_shot.png");

    }

    @When("Menu nav item default")
    public void menu_nav_item_default(){
        //Tong quan
        WebElement tongquan = driver.findElement(TongQuan);
        Assert.assertEquals(tongquan.getText(), "Tổng quan");
        Assert.assertEquals(tongquan.getAttribute("href"), "https://book-cms.vmgmedia.vn/dashboard");
        WebElement icon_overview = driver.findElement(iconTQ);
        Assert.assertEquals(icon_overview.getCssValue("--svg"), "url(\"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' width='24' height='24'%3E%3Cg fill='none' stroke='black' stroke-linecap='round' stroke-linejoin='round' stroke-width='2'%3E%3Cpath d='M10 3.2A9 9 0 1 0 20.8 14a1 1 0 0 0-1-1H13a2 2 0 0 1-2-2V4a.9.9 0 0 0-1-.8'/%3E%3Cpath d='M15 3.5A9 9 0 0 1 20.5 9H16a1 1 0 0 1-1-1z'/%3E%3C/g%3E%3C/svg%3E\")");
        System.out.println("Tong quan Pass");

        //Danh mục sách
        WebElement dms = driver.findElement(DanhMucSach);
        Assert.assertEquals(dms.getText(), "Danh mục sách");
        Assert.assertEquals(dms.getAttribute("href"), "https://book-cms.vmgmedia.vn/content-management/categories");
        WebElement icon_dms = driver.findElement(iconDMS);
        Assert.assertEquals(icon_dms.getCssValue("--svg"), "url(\"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' width='24' height='24'%3E%3Cg fill='none' stroke='black' stroke-linecap='round' stroke-linejoin='round' stroke-width='2'%3E%3Cpath d='M9 4h3l2 2h5a2 2 0 0 1 2 2v7a2 2 0 0 1-2 2H9a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2'/%3E%3Cpath d='M17 17v2a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-9a2 2 0 0 1 2-2h2'/%3E%3C/g%3E%3C/svg%3E\")");
        System.out.println("Danh muc sach Pass");

        //Tác giả
        WebElement author = driver.findElement(TacGia);
        Assert.assertEquals(author.getText(), "Tác giả");
        Assert.assertEquals(author.getAttribute("href"), "https://book-cms.vmgmedia.vn/content-management/author");
        WebElement icon_author = driver.findElement(iconTacGia);
        Assert.assertEquals(icon_author.getCssValue("--svg"), "url(\"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' width='24' height='24'%3E%3Cg fill='none' stroke='black' stroke-linecap='round' stroke-linejoin='round' stroke-width='2'%3E%3Cpath d='M22 9L12 5L2 9l10 4zv6'/%3E%3Cpath d='M6 10.6V16a6 3 0 0 0 12 0v-5.4'/%3E%3C/g%3E%3C/svg%3E\")");
        System.out.println("Tac gia Pass");

        //Sách
        WebElement book = driver.findElement(Sach);
        Assert.assertEquals(book.getText(), "Sách");
        Assert.assertEquals(book.getAttribute("href"), "https://book-cms.vmgmedia.vn/content-management/books");
        WebElement icon_book = driver.findElement(iconSach);
        Assert.assertEquals(icon_book.getCssValue("--svg"), "url(\"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' width='24' height='24'%3E%3Cg fill='none' stroke='black' stroke-linecap='round' stroke-linejoin='round' stroke-width='2'%3E%3Cpath d='M5 5a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H6a1 1 0 0 1-1-1zm4 0a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1h-2a1 1 0 0 1-1-1zM5 8h4m0 8h4'/%3E%3Cpath d='m13.803 4.56l2.184-.53c.562-.135 1.133.19 1.282.732l3.695 13.418a1.02 1.02 0 0 1-.634 1.219l-.133.041l-2.184.53c-.562.135-1.133-.19-1.282-.732L13.036 5.82a1.02 1.02 0 0 1 .634-1.219zM14 9l4-1m-2 8l3.923-.98'/%3E%3C/g%3E%3C/svg%3E\")");
        System.out.println("Sach Pass");

        //Bình luận/Đánh giá
        WebElement comment = driver.findElement(BinhLuan);
        Assert.assertEquals(comment.getText(), "Bình luận/Đánh giá");
        Assert.assertEquals(comment.getAttribute("href"), "https://book-cms.vmgmedia.vn/content-management/comment-evaluation");
        WebElement icon_cmt = driver.findElement(iconComment);
        Assert.assertEquals(icon_cmt.getCssValue("--svg"), "url(\"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' width='24' height='24'%3E%3Cg fill='none' stroke='black' stroke-linecap='round' stroke-linejoin='round' stroke-width='2'%3E%3Cpath d='M17.802 17.292s.077-.055.2-.149c1.843-1.425 3-3.49 3-5.789c0-4.286-4.03-7.764-9-7.764s-9 3.478-9 7.764c0 4.288 4.03 7.646 9 7.646c.424 0 1.12-.028 2.088-.084c1.262.82 3.104 1.493 4.716 1.493c.499 0 .734-.41.414-.828c-.486-.596-1.156-1.551-1.416-2.29z'/%3E%3Cpath d='M7.5 13.5c2.5 2.5 6.5 2.5 9 0'/%3E%3C/g%3E%3C/svg%3E\")");
        System.out.println("Binh luan/Danh gia Pass");

        //Giao dịch
        WebElement transaction = driver.findElement(GiaoDich);
        Assert.assertEquals(transaction.getText(), "Giao dịch");
        Boolean en_icon_right_trans = common.checkDisplay(icon_right_trans);
        Assert.assertEquals(en_icon_right_trans, true);
//        WebElement iconRightTrans = driver.findElement(icon_right_trans);
//        Assert.assertEquals(iconRightTrans.getAttribute("class"), "mui-1ewczcp"); //Icon hiển thị mặc định
        WebElement icon_trans = driver.findElement(iconTransaction);
        Assert.assertEquals(icon_trans.getCssValue("--svg"), "url(\"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' width='24' height='24'%3E%3Cg fill='none' stroke='black' stroke-linecap='round' stroke-linejoin='round' stroke-width='2'%3E%3Cpath d='M3 12a9 9 0 1 0 18 0a9 9 0 0 0-18 0m9-3h.01'/%3E%3Cpath d='M11 12h1v4h1'/%3E%3C/g%3E%3C/svg%3E\")");
        System.out.println("Giao dich Pass");

        //In-App Purchases
        WebElement iap = driver.findElement(IAP);
        Assert.assertEquals(iap.getText(), "In-App Purchases");
        Assert.assertEquals(iap.getAttribute("href"), "https://book-cms.vmgmedia.vn/transaction-iap/in-app-purchase");
        WebElement icon_iap = driver.findElement(iconIAP);
        Assert.assertEquals(icon_iap.getCssValue("--svg"), "url(\"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' width='24' height='24'%3E%3Cpath fill='none' stroke='black' stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M19.875 6.27A2.225 2.225 0 0 1 21 8.218v7.284c0 .809-.443 1.555-1.158 1.948l-6.75 4.27a2.269 2.269 0 0 1-2.184 0l-6.75-4.27A2.225 2.225 0 0 1 3 15.502V8.217c0-.809.443-1.554 1.158-1.947l6.75-3.98a2.33 2.33 0 0 1 2.25 0l6.75 3.98z'/%3E%3C/svg%3E\")");
        System.out.println("In-App Purchases Pass");

        //Tài khoản khách hàng
        WebElement accCus = driver.findElement(TaiKhoanKH);
        Assert.assertEquals(accCus.getText(), "Tài khoản khách hàng");
        Assert.assertEquals(accCus.getAttribute("href"), "https://book-cms.vmgmedia.vn/system-management/accounts");
        WebElement icon_cus = driver.findElement(iconAccCus);
        Assert.assertEquals(icon_cus.getCssValue("--svg"), "url(\"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' width='24' height='24'%3E%3Cpath fill='none' stroke='black' stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M5 7a4 4 0 1 0 8 0a4 4 0 1 0-8 0M3 21v-2a4 4 0 0 1 4-4h4a4 4 0 0 1 4 4v2m1-17.87a4 4 0 0 1 0 7.75M21 21v-2a4 4 0 0 0-3-3.85'/%3E%3C/svg%3E\")");
        System.out.println("Tai khoan khach hang Pass");

        //Quản trị viên
        WebElement administrator = driver.findElement(QTV);
        Assert.assertEquals(administrator.getText(), "Quản trị viên");
        Boolean en_icon_right_administrator = common.checkDisplay(icon_right_QTV);
        Assert.assertEquals(en_icon_right_administrator, true);
//        WebElement iconRightQTV = driver.findElement(icon_right_QTV);
//        Assert.assertEquals(iconRightQTV.getAttribute("class"), "mui-1ewczcp"); //Icon hiển thị mặc định
        WebElement icon_admin = driver.findElement(iconQTV);
        Assert.assertEquals(icon_admin.getCssValue("--svg"), "url(\"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' width='24' height='24'%3E%3Cg fill='none' stroke='black' stroke-linecap='round' stroke-linejoin='round' stroke-width='2'%3E%3Cpath d='M12 3a12 12 0 0 0 8.5 3A12 12 0 0 1 12 21A12 12 0 0 1 3.5 6A12 12 0 0 0 12 3'/%3E%3Cpath d='M11 11a1 1 0 1 0 2 0a1 1 0 1 0-2 0m1 1v2.5'/%3E%3C/g%3E%3C/svg%3E\")");
        System.out.println("Quan tri vien Pass");

        //Thông báo
        WebElement noti = driver.findElement(ThongBao);
        Assert.assertEquals(noti.getText(), "Thông báo");
        Assert.assertEquals(noti.getAttribute("href"), "https://book-cms.vmgmedia.vn/system-management/notifications");
        WebElement icon_noti = driver.findElement(iconNoti);
        Assert.assertEquals(icon_noti.getCssValue("--svg"), "url(\"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' width='24' height='24'%3E%3Cpath fill='none' stroke='black' stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M10 5a2 2 0 1 1 4 0a7 7 0 0 1 4 6v3a4 4 0 0 0 2 3H4a4 4 0 0 0 2-3v-3a7 7 0 0 1 4-6M9 17v1a3 3 0 0 0 6 0v-1'/%3E%3C/svg%3E\")");

        System.out.println("Thong bao Pass");
    }

    //Submenu Giao dịch
    @When("Submenu nav item - Transaction")
    public void submenu_nav_item_transaction(){
        //Ktra text & icon submenu Mua sách
        WebElement muasach = driver.findElement(MuaSach);
        Assert.assertEquals(muasach.getText(), "Mua sách");
        Assert.assertEquals(muasach.getAttribute("href"), "https://book-cms.vmgmedia.vn/transaction-iap/purchase-book");
        WebElement icon_muasach = driver.findElement(By.xpath("//i[@class='tabler-book-2']"));
        Assert.assertEquals(icon_muasach.getCssValue("--svg"), "url(\"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' width='24' height='24'%3E%3Cg fill='none' stroke='black' stroke-linecap='round' stroke-linejoin='round' stroke-width='2'%3E%3Cpath d='M19 4v16H7a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2z'/%3E%3Cpath d='M19 16H7a2 2 0 0 0-2 2M9 8h6'/%3E%3C/g%3E%3C/svg%3E\")");
        System.out.println("Mua sach Pass");

        //Ktra text & icon submenu Mua xu
        WebElement muaxu = driver.findElement(MuaXu);
        Assert.assertEquals(muaxu.getText(), "Mua xu");
        Assert.assertEquals(muaxu.getAttribute("href"), "https://book-cms.vmgmedia.vn/transaction-iap/purchase-coin");
        WebElement icon_muaxu = driver.findElement(By.xpath("//i[@class='tabler-coin']"));
        Assert.assertEquals(icon_muaxu.getCssValue("--svg"), "url(\"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' width='24' height='24'%3E%3Cg fill='none' stroke='black' stroke-linecap='round' stroke-linejoin='round' stroke-width='2'%3E%3Cpath d='M3 12a9 9 0 1 0 18 0a9 9 0 1 0-18 0'/%3E%3Cpath d='M14.8 9A2 2 0 0 0 13 8h-2a2 2 0 1 0 0 4h2a2 2 0 1 1 0 4h-2a2 2 0 0 1-1.8-1M12 7v10'/%3E%3C/g%3E%3C/svg%3E\")");
        System.out.println("Mua xu Pass");
    }

    //Submenu Quản trị viên
    @When("Submenu nav item - Administrator")
    public void submenu_nav_item_administrator(){
        //Ktra text & icon submenu Tài khoản quản trị viên
        WebElement accQTV = driver.findElement(TaiKhoanQTV);
        Assert.assertEquals(accQTV.getText(), "Tài khoản quản trị viên");
        Assert.assertEquals(accQTV.getAttribute("href"), "https://book-cms.vmgmedia.vn/permission-management/admin");
        System.out.println("Tai khoan quan tri vien Pass");

        //Ktra text & icon submenu Phân quyền
        WebElement permission = driver.findElement(PhanQuyen);
        Assert.assertEquals(permission.getText(), "Phân quyền");
        Assert.assertEquals(permission.getAttribute("href"), "https://book-cms.vmgmedia.vn/permission-management/permission");
        System.out.println("Phan quyen Pass");

        //Ktra text & icon submenu Vai trò
        WebElement role = driver.findElement(PhanQuyen);
        Assert.assertEquals(role.getText(), "Vai trò");
        Assert.assertEquals(role.getAttribute("href"), "https://book-cms.vmgmedia.vn/permission-management/role");
        System.out.println("Vai tro Pass");
    }


    /**
     * Nhấn (click) vào giao diện menu
     */
    @And("Tap the menu logo")
    public void tap_the_menu_logo(){
        common.clickElement(Logo);
        common.waitForPageLoaded();
        System.out.println("Pass");
    }

    @And("Click on menu item Tong quan")
    public void click_on_menu_item_tong_quan(){
        common.clickElement(TongQuan);
        common.waitForPageLoaded();
        System.out.println("Pass");
    }

    @And("Click on menu item Danh muc sach")
    public void click_on_menu_item_danh_muc_sach(){
        common.clickElement(DanhMucSach);
        common.waitForPageLoaded();
        System.out.println("Pass");
    }

    @And("Click on menu item Tac gia")
    public void click_on_menu_item_tac_gia(){
        common.clickElement(TacGia);
        common.waitForPageLoaded();
        System.out.println("Pass");
    }

    @And("Click on menu item Sach")
    public void click_on_menu_item_sach(){
        common.clickElement(Sach);
        common.waitForPageLoaded();
        System.out.println("Pass");
    }

    @And("Click on menu item Binh luan Danh gia")
    public void click_on_menu_item_binh_luan_danh_gia(){
        common.clickElement(BinhLuan);
        common.waitForPageLoaded();
        System.out.println("Pass");
    }

    @And("Click on menu item Giao dich")
    public void click_on_menu_item_giao_dich(){
        MenuSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(GiaoDich);
        System.out.println("Pass");
    }

    @And("Click on submenu item Mua sach")
    public void click_on_submenu_item_mua_sach(){
        MenuSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(MuaSach);
        common.waitForPageLoaded();
        System.out.println("Pass");
    }

    @And("Click on submenu item Mua xu")
    public void click_on_submenu_item_mua_xu(){
        MenuSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(MuaXu);
        common.waitForPageLoaded();
        System.out.println("Pass");
    }

    @And("Click on menu item In-App Purchases")
    public void click_on_menu_item_iap(){
        common.clickElement(IAP);
        common.waitForPageLoaded();
        System.out.println("Pass");
    }

    @And("Click on menu item Tai khoan khach hang")
    public void click_on_menu_item_tai_khoan_khach_hang(){
        common.clickElement(TaiKhoanKH);
        common.waitForPageLoaded();
        System.out.println("Pass");
    }

    @And("Click on menu item Quan tri vien")
    public void click_on_menu_item_quan_tri_vien(){
        MenuSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(QTV);
        System.out.println("Pass");
    }

    @And("Click on submenu item Tai khoan quan tri vien")
    public void click_on_submenu_item_tai_khoan_quan_tri_vien(){
        MenuSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(TaiKhoanQTV);
        common.waitForPageLoaded();
        System.out.println("Pass");
    }

    @And("Click on submenu item Phan quyen")
    public void click_on_submenu_item_quan_quyen(){
        MenuSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(PhanQuyen);
        common.waitForPageLoaded();
        System.out.println("Pass");
    }

    @And("Click on submenu item Vai tro")
    public void click_on_submenu_item_vai_tro(){
        MenuSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(VaiTro);
        common.waitForPageLoaded();
        System.out.println("Pass");
    }

    @And("Click on menu item Thong bao")
    public void click_on_menu_item_thong_bao(){
        common.clickElement(ThongBao);
        common.waitForPageLoaded();
        System.out.println("Pass");
    }

    @And("Click circle dot")
    public void click_circle_dot(){
        MenuSteps.getInstance().ImplicitlyWait_Config();
        WebElement circle = driver.findElement(CircleDot);
        circle.click();
        System.out.println("Pass");
    }

    @And("Hover menu and click circle dot")
    public void hover_menu_and_click_circle_dot(){
        MenuSteps.getInstance().ImplicitlyWait_Config();
        common.hoverAndClick(Menu, CircleDot);
        System.out.println("Pass");
    }

    @And("Hover menu")
    public void hover_menu(){
        MenuSteps.getInstance().ImplicitlyWait_Config();
        common.hover(Menu);
        System.out.println("Pass");
    }

    /**
     * Giao diện menu mini
     */
    @Then("Menu mini UI")
    public void menu_mini_ui(){
        //Icon tổng quan
        Boolean en_icon_tq = common.checkDisplay(iconTQ);
        Assert.assertEquals(en_icon_tq, true);

        By text_tong_quan = By.xpath("//a[@label='Tổng quan']/span[2]");
        Boolean en_tq = common.checkDisplay(text_tong_quan);
        Assert.assertEquals(en_tq, false);

        System.out.println("Icon tong quan Pass");

        //Icon danh mục sách
        Boolean en_icon_dms = common.checkDisplay(iconDMS);
        Assert.assertEquals(en_icon_dms, true);

        By text_dms = By.xpath("//a[@label='Danh mục sách']/span[2]");
        Boolean en_dms = common.checkDisplay(text_dms);
        Assert.assertEquals(en_dms, false);
        System.out.println("Icon danh muc sach Pass");

        //Icon tác giả
        Boolean en_icon_tac_gia = common.checkDisplay(iconTacGia);
        Assert.assertEquals(en_icon_tac_gia, true);

        By text_author = By.xpath("//a[@label='Tác giả']/span[2]");
        Boolean en_author = common.checkDisplay(text_author);
        Assert.assertEquals(en_author, false);
        System.out.println("Icon tac gia Pass");

        //Icon sách
        Boolean en_icon_book = common.checkDisplay(iconSach);
        Assert.assertEquals(en_icon_book, true);

        By text_book = By.xpath("//a[@label='Sách']/span[2]");
        Boolean en_book = common.checkDisplay(text_book);
        Assert.assertEquals(en_book, false);
        System.out.println("Icon sach Pass");

        //Icon bình luận/ đánh giá
        Boolean en_icon_comment = common.checkDisplay(iconComment);
        Assert.assertEquals(en_icon_comment, true);

        By text_cmt = By.xpath("//a[@label='Bình luận/Đánh giá']/span[2]");
        Boolean en_cmt = common.checkDisplay(text_cmt);
        Assert.assertEquals(en_cmt, false);
        System.out.println("Icon binh luan/danh gia Pass");

        //Icon giao dịch
        Boolean en_icon_trans = common.checkDisplay(iconTransaction);
        Assert.assertEquals(en_icon_trans, true);

        By text_trans = By.xpath("//nav/ul/li[3]/ul/li[2]/a[@class='ts-menu-button']/span[2]");
        Boolean en_trans = common.checkDisplay(text_trans);
        Assert.assertEquals(en_trans, false);

        Boolean en_icon_right_trans = common.checkDisplay(icon_right_trans);
        Assert.assertEquals(en_icon_right_trans, false);
        System.out.println("Icon giao dich Pass");

        //Icon in-app purchases
        Boolean en_icon_iap = common.checkDisplay(iconIAP);
        Assert.assertEquals(en_icon_iap, true);

        By text_iap = By.xpath("//a[@label='In-App Purchases']/span[2]");
        Boolean en_iap = common.checkDisplay(text_iap);
        Assert.assertEquals(en_iap, false);
        System.out.println("Icon in-app purchases Pass");

        //Icon tài khoản khách hàng
        Boolean en_icon_cus = common.checkDisplay(iconAccCus);
        Assert.assertEquals(en_icon_cus, true);

        By text_cus = By.xpath("//a[@label='Tài khoản khách hàng']/span[2]");
        Boolean en_acc_cus = common.checkDisplay(text_cus);
        Assert.assertEquals(en_acc_cus, false);
        System.out.println("Icon tai khoan khach hang Pass");

        //Icon quản trị viên
        Boolean en_icon_admin = common.checkDisplay(iconQTV);
        Assert.assertEquals(en_icon_admin, true);

        By text_admin = By.xpath("//nav/ul/li[4]/ul/li[3]/a[@class='ts-menu-button']/span[2]");
        Boolean en_admin = common.checkDisplay(text_admin);
        Assert.assertEquals(en_admin, false);

        Boolean en_icon_right_admin = common.checkDisplay(icon_right_QTV);
        Assert.assertEquals(en_icon_right_admin, false);
        System.out.println("Icon quan tri vien Pass");

        //Icon thông báo
        Boolean en_icon_noti = common.checkDisplay(iconNoti);
        Assert.assertEquals(en_icon_noti, true);

        By text_noti = By.xpath("//a[@label='Thông báo']/span[2]");
        Boolean en_noti = common.checkDisplay(text_noti);
        Assert.assertEquals(en_noti, false);
        System.out.println("Icon thong bao Pass");
    }


    /**
     * Kết quả khi thực hiện các action vào menu
     */
    @Then("Menu result: open book screen")
    public void menu_result_open_book_screen(){
        Assert.assertEquals(driver.getCurrentUrl(), "https://book-cms.vmgmedia.vn/content-management/books");

        WebElement book = driver.findElement(Sach);
        String background_actual = book.getCssValue("background");
        String background_expect = "linear-gradient(270deg, rgba(115, 103, 240, 0.7) 0%, rgb(115, 103, 240) 100%)";
        Assert.assertEquals(background_actual, background_expect);

        System.out.println("Mở màn hình Sách thành công");
    }

    @Then("Menu result: open overview screen")
    public void menu_result_open_overview_screen(){
        Assert.assertEquals(driver.getCurrentUrl(), "https://book-cms.vmgmedia.vn/dashboard");

        WebElement tq = driver.findElement(TongQuan);
        String background_actual = tq.getCssValue("background");
        String background_expect = "linear-gradient(270deg, rgba(115, 103, 240, 0.7) 0%, rgb(115, 103, 240) 100%)";
        Assert.assertEquals(background_actual, background_expect);

        System.out.println("Mở màn hình Tổng quan thành công");
    }

    @Then("Menu result: open book list screen")
    public void menu_result_open_book_list_screen(){
        Assert.assertEquals(driver.getCurrentUrl(), "https://book-cms.vmgmedia.vn/content-management/categories");

        WebElement bookList = driver.findElement(DanhMucSach);
        String background_actual = bookList.getCssValue("background");
        String background_expect = "linear-gradient(270deg, rgba(115, 103, 240, 0.7) 0%, rgb(115, 103, 240) 100%)";
        Assert.assertEquals(background_actual, background_expect);

        System.out.println("Mở màn hình Danh mục sách thành công");
    }

    @Then("Menu result: open author screen")
    public void menu_result_open_author_screen(){
        Assert.assertEquals(driver.getCurrentUrl(), "https://book-cms.vmgmedia.vn/content-management/author");

        WebElement author = driver.findElement(TacGia);
        String background_actual = author.getCssValue("background");
        String background_expect = "linear-gradient(270deg, rgba(115, 103, 240, 0.7) 0%, rgb(115, 103, 240) 100%)";
        Assert.assertEquals(background_actual, background_expect);

        System.out.println("Mở màn hình Tác giả thành công");
    }

    @Then("Menu result: open comment reviews screen")
    public void menu_result_open_comments_reviews_screen(){
        Assert.assertEquals(driver.getCurrentUrl(), "https://book-cms.vmgmedia.vn/content-management/comment-evaluation");

        WebElement cmt = driver.findElement(BinhLuan);
        String background_actual = cmt.getCssValue("background");
        String background_expect = "linear-gradient(270deg, rgba(115, 103, 240, 0.7) 0%, rgb(115, 103, 240) 100%)";
        Assert.assertEquals(background_actual, background_expect);

        System.out.println("Mở màn hình Bình luận/Đánh giá thành công");
    }

    @Then("Menu result: open in-app purchases screen")
    public void menu_result_open_iap_screen(){
        Assert.assertEquals(driver.getCurrentUrl(), "https://book-cms.vmgmedia.vn/transaction-iap/in-app-purchase");

        WebElement iap = driver.findElement(IAP);
        String background_actual = iap.getCssValue("background");
        String background_expect = "linear-gradient(270deg, rgba(115, 103, 240, 0.7) 0%, rgb(115, 103, 240) 100%)";
        Assert.assertEquals(background_actual, background_expect);

        System.out.println("Mở màn hình In-App Purchases thành công");
    }

    @Then("Menu result: open customer account screen")
    public void menu_result_open_customer_account_screen(){
        Assert.assertEquals(driver.getCurrentUrl(), "https://book-cms.vmgmedia.vn/system-management/accounts");

        WebElement accCus = driver.findElement(TaiKhoanKH);
        String background_actual = accCus.getCssValue("background");
        String background_expect = "linear-gradient(270deg, rgba(115, 103, 240, 0.7) 0%, rgb(115, 103, 240) 100%)";
        Assert.assertEquals(background_actual, background_expect);

        System.out.println("Mở màn hình Tài khoản khách hàng thành công");
    }

    @Then("Menu result: open notify screen")
    public void menu_result_open_notify_screen(){
        Assert.assertEquals(driver.getCurrentUrl(), "https://book-cms.vmgmedia.vn/system-management/notifications");

        WebElement book = driver.findElement(ThongBao);
        String background_actual = book.getCssValue("background");
        String background_expect = "linear-gradient(270deg, rgba(115, 103, 240, 0.7) 0%, rgb(115, 103, 240) 100%)";
        Assert.assertEquals(background_actual, background_expect);

        System.out.println("Mở màn hình Thông báo thành công");
    }

    @Then("Menu result: show transaction submenu")
    public void menu_result_show_transaction_submenu() {
        // Kiểm tra hiển thị submenu Mua sách
        Boolean en_mua_sach = common.checkDisplay(MuaSach);
        MenuSteps.getInstance().ImplicitlyWait_Config();
        System.out.println(en_mua_sach);
        Assert.assertEquals(en_mua_sach, true);
        System.out.println("Hiển thị submenu Mua sách");

        // Kiểm tra hiển thị submenu Mua xu
        Boolean en_mua_xu = common.checkDisplay(MuaXu);
        MenuSteps.getInstance().ImplicitlyWait_Config();
        System.out.println(en_mua_xu);
        Assert.assertEquals(en_mua_xu, true);
        System.out.println("Hiển thị submenu Mua xu");
    }

    @Then("Menu result: show administrator submenu")
    public void menu_result_show_administrator_submenu() {
        // Kiểm tra hiển thị submenu Tài khoản quản trị viên
        Boolean en_acc_qtv = common.checkDisplay(TaiKhoanQTV);
        MenuSteps.getInstance().ImplicitlyWait_Config();
        System.out.println(en_acc_qtv);
        Assert.assertEquals(en_acc_qtv, true);
        System.out.println("Hiển thị submenu Tài khoản quản trị viên");

        // Kiểm tra hiển thị submenu Phân Quyền
        Boolean en_permission = common.checkDisplay(PhanQuyen);
        MenuSteps.getInstance().ImplicitlyWait_Config();
        System.out.println(en_permission);
        Assert.assertEquals(en_permission, true);
        System.out.println("Hiển thị submenu Phân Quyền");

        // Kiểm tra hiển thị submenu Vai trò
        Boolean en_role = common.checkDisplay(VaiTro);
        MenuSteps.getInstance().ImplicitlyWait_Config();
        System.out.println(en_role);
        Assert.assertEquals(en_role, true);
        System.out.println("Hiển thị submenu Vai trò");
    }

    @Then("Menu result: open purchases book screen")
    public void menu_result_open_purchases_book_screen(){
        Assert.assertEquals(driver.getCurrentUrl(), "https://book-cms.vmgmedia.vn/transaction-iap/purchase-book");

        WebElement book = driver.findElement(MuaSach);
        String background_actual = book.getCssValue("background");
        String background_expect = "linear-gradient(270deg, rgba(115, 103, 240, 0.7) 0%, rgb(115, 103, 240) 100%)";
        Assert.assertEquals(background_actual, background_expect);

        System.out.println("Mở màn hình Mua sách thành công");
    }

    @Then("Menu result: open purchases coin screen")
    public void menu_result_open_purchases_coin_screen(){
        Assert.assertEquals(driver.getCurrentUrl(), "https://book-cms.vmgmedia.vn/transaction-iap/purchase-coin");

        WebElement book = driver.findElement(MuaXu);
        String background_actual = book.getCssValue("background");
        String background_expect = "linear-gradient(270deg, rgba(115, 103, 240, 0.7) 0%, rgb(115, 103, 240) 100%)";
        Assert.assertEquals(background_actual, background_expect);

        System.out.println("Mở màn hình Mua xu thành công");
    }

    @Then("Menu result: open admin account screen")
    public void menu_result_open_admin_account_screen(){
        Assert.assertEquals(driver.getCurrentUrl(), "https://book-cms.vmgmedia.vn/permission-management/admin");

        WebElement book = driver.findElement(TaiKhoanQTV);
        String background_actual = book.getCssValue("background");
        String background_expect = "linear-gradient(270deg, rgba(115, 103, 240, 0.7) 0%, rgb(115, 103, 240) 100%)";
        Assert.assertEquals(background_actual, background_expect);

        System.out.println("Mở màn hình Tài khoản quản trị viên thành công");
    }

    @Then("Menu result: open permission screen")
    public void menu_result_open_permission_screen(){
        Assert.assertEquals(driver.getCurrentUrl(), "https://book-cms.vmgmedia.vn/permission-management/permission");

        WebElement book = driver.findElement(PhanQuyen);
        String background_actual = book.getCssValue("background");
        String background_expect = "linear-gradient(270deg, rgba(115, 103, 240, 0.7) 0%, rgb(115, 103, 240) 100%)";
        Assert.assertEquals(background_actual, background_expect);

        System.out.println("Mở màn hình Phân quyền thành công");
    }

    @Then("Menu result: open role screen")
    public void menu_result_open_role_screen(){
        Assert.assertEquals(driver.getCurrentUrl(), "https://book-cms.vmgmedia.vn/permission-management/role");

        WebElement book = driver.findElement(VaiTro);
        String background_actual = book.getCssValue("background");
        String background_expect = "linear-gradient(270deg, rgba(115, 103, 240, 0.7) 0%, rgb(115, 103, 240) 100%)";
        Assert.assertEquals(background_actual, background_expect);

        System.out.println("Mở màn hình Vai trò thành công");
    }
}
