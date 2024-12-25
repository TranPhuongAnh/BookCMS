package StepDefinitions;

import configProject.CommonMethod;
import configProject.ConfigFileReader;
import configProject.WebDriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kong.unirest.JsonNode;
import org.json.simple.JSONArray;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class bookSteps extends WebDriverManager {
    WebDriver driver = null;
    ConfigFileReader config = new ConfigFileReader();
    CommonMethod common = new CommonMethod(getDriver());

    // Info Login CMS
    private String email = config.getUserLogin();
    private String password = config.getPassLogin();

    // Header
    private By Screen = By.xpath("//body");
    private By TextHeader = By.xpath("//h1");
    private By IconHeader = By.xpath("//i[@class='tabler-books text-[30px]']");
    private By User = By.xpath("//span[@class='MuiBadge-root mis-2 mui-1rzb3uu']");

    // Add book
    private By BtnAddBook = By.xpath("//main/div/div/div[1]/div[2]/a/button");

    // Filter
    private By InputSearch = By.xpath("//input[@placeholder='Tìm kiếm tên sách']");
    private By AdvancedFilters = By.xpath("//main/div/div/div[1]/div[2]/button");
    private By FilterType = By.xpath("//input[@placeholder='Lọc theo thể loại']");
    private By FilterStatus = By.xpath("//input[@placeholder='Lọc theo trạng thái']");
    private By FilterAuthor = By.xpath("//input[@placeholder='Nhập tên tác giả để tìm kiếm']");
    private By FilterSortBy = By.xpath("//input[@placeholder='Sắp xếp theo']");

    // Table - Header
    private By Th_Book = By.xpath("//table/thead/tr/th[1]");
    private By Th_Author = By.xpath("//table/thead/tr/th[2]");
    private By Th_Status = By.xpath("//table/thead/tr/th[3]");
    private By Th_Audio = By.xpath("//table/thead/tr/th[4]");
    private By Th_CoinPrice = By.xpath("//table/thead/tr/th[5]");
    private By Th_Action = By.xpath("//i[@class='tabler-menu-2 w-[22px] h-[22px]']");

    // Table - Data record



    /**
     * Các hàm chung
     */
    @Given("Book: Open book screen")
    public void book_open_book_screen(){
        bookSteps.getInstance().ImplicitlyWait_Config();
        // Mở trình duyệt
        driver = getDriver();
        driver.navigate().to(config.getApplicationUrl() + "login");
        common.waitForPageLoaded();

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

        // Nhấn menu item Sách
        bookSteps.getInstance().ImplicitlyWait_Config();
        By Sach = By.xpath("//a[@label='Sách']");
        common.clickElement(Sach);
        common.waitForPageLoaded();

        System.out.println("Thành công mở màn hình Sách");
    }

    @And("Book: Reload page")
    public void book_reload_page(){
        driver.navigate().refresh();
        common.waitForPageLoaded();
        System.out.println("Reload page thành công");
    }

    @And("Book: Click background screen")
    public void book_click_background_screen(){
        bookSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(Screen);
        System.out.println("Pass");
    }

    @And("Book: Close browser")
    public void book_close_browser() throws InterruptedException {
        bookSteps.getInstance().CloseDriver();
    }


    /**
     * Header: Giao diện và action
     */
    @When("Book: Header UI")
    public void book_header_ui(){
        // Icon
        Boolean en_icon_book = common.checkDisplay(IconHeader);
        Assert.assertEquals(en_icon_book, true);

        WebElement iconBook = driver.findElement(IconHeader);
        Assert.assertEquals(iconBook.getCssValue("font-size"), "30px");
        Assert.assertEquals(iconBook.getCssValue("background-color"), "rgba(47, 43, 61, 0.9)");
        System.out.println("Icon book Pass");

        // Title
        Boolean en_text = common.checkDisplay(TextHeader);
        Assert.assertEquals(en_text, true);

        WebElement text = driver.findElement(TextHeader);
        Assert.assertEquals(text.getText(), "Quản lý sách");
        Assert.assertEquals(text.getCssValue("font-size"), "22px");
        Assert.assertEquals(text.getCssValue("font-weight"), "600");
        Assert.assertEquals(text.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        System.out.println("Title header Pass");

        // User
        Boolean en_user = common.checkDisplay(User);
        Assert.assertEquals(en_user, true);

        WebElement person_icon = driver.findElement(By.xpath("//span[@class='MuiBadge-root mis-2 mui-1rzb3uu']/div"));
        Assert.assertEquals(person_icon.getCssValue("cursor"), "pointer");
        Assert.assertEquals(person_icon.getCssValue("block-size"), "38px");
        Assert.assertEquals(person_icon.getCssValue("border-radius"), "50%");
        Assert.assertEquals(person_icon.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        Assert.assertEquals(person_icon.getCssValue("background-color"), "rgb(238, 237, 240)");

        WebElement activity_icon = driver.findElement(By.xpath("//span[@class='mui-1dcxyp3']"));
        Assert.assertEquals(activity_icon.getCssValue("cursor"), "pointer");
        Assert.assertEquals(activity_icon.getCssValue("width"), "8px");
        Assert.assertEquals(activity_icon.getCssValue("height"), "8px");
        Assert.assertEquals(activity_icon.getCssValue("border-radius"), "50%");
        Assert.assertEquals(activity_icon.getCssValue("background-color"), "rgb(40, 199, 111)");
        System.out.println("User Pass");
    }

    @And("Book: Click user")
    public void book_click_user(){
        bookSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(User);
        System.out.println("Pass");
    }

    @Then("Book: Popup person")
    public void book_popup_person(){
        // Ktra popup
        By popup = By.className("shadow-lg");
        Boolean en_popup = common.checkDisplay(popup);
        Assert.assertEquals(en_popup, true);
        WebElement popup_ele = driver.findElement(popup);
        Assert.assertEquals(popup_ele.getCssValue("background-color"), "rgb(255, 255, 255)");
        System.out.println("Popup Pass");
    }

    /**
     * Filter: Giao diện & action
     */
    @Then("Book filter: Default UI")
    public void book_filter_default_ui(){
        // Input search
        bookSteps.getInstance().ImplicitlyWait_Config();
        Boolean en_search = common.checkDisplay(InputSearch);
        Assert.assertEquals(en_search, true);

        // Button Advance filter
        bookSteps.getInstance().ImplicitlyWait_Config();
        Boolean en_filter = common.checkDisplay(AdvancedFilters);
        Assert.assertEquals(en_filter, true);
    }

    @Then("Book filter: Default detail UI")
    public void book_filter_default_detail_ui(){
        // Input search
        WebElement search = driver.findElement(InputSearch);
        Assert.assertEquals(search.getAttribute("placeholder"), "Tìm kiếm tên sách");
        Assert.assertEquals(search.getAttribute("type"), "text");
        Assert.assertEquals(search.getCssValue("font-size"), "15px");
        Assert.assertEquals(search.getCssValue("font-weight"), "400");
        System.out.println("Input search Pass");

        // Button Advance filter
        WebElement advFilter = driver.findElement(AdvancedFilters);
        Assert.assertEquals(advFilter.getText(), "Bộ lọc nâng cao");
        Assert.assertEquals(advFilter.getCssValue("color"), "rgb(128, 131, 144)");
        Assert.assertEquals(advFilter.getCssValue("font-size"), "16px");
        Assert.assertEquals(advFilter.getCssValue("font-weight"), "400");
        Assert.assertEquals(advFilter.getCssValue("border-color"), "rgb(128, 131, 144)");
        Assert.assertEquals(advFilter.getCssValue("background-color"), "rgba(0, 0, 0, 0)");
        System.out.println("Button Advance filter Pass");
    }

    @And("Book filter: Click advance filter")
    public void book_click_advance_filter(){
        bookSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(AdvancedFilters);
        System.out.println("Pass");
    }

    @Then("Book filter: Advanced filter UI")
    public void book_advanced_filter_ui(){
        // Type
        bookSteps.getInstance().ImplicitlyWait_Config();
        Boolean en_type = common.checkDisplay(FilterType);
        Assert.assertEquals(en_type, true);

        // Status
        bookSteps.getInstance().ImplicitlyWait_Config();
        Boolean en_status = common.checkDisplay(FilterStatus);
        Assert.assertEquals(en_status, true);

        // Author
        bookSteps.getInstance().ImplicitlyWait_Config();
        Boolean en_author = common.checkDisplay(FilterAuthor);
        Assert.assertEquals(en_author, true);

        // Sort by
        bookSteps.getInstance().ImplicitlyWait_Config();
        Boolean en_sb = common.checkDisplay(FilterSortBy);
        Assert.assertEquals(en_sb, true);
    }

    @Then("Book filter: Advanced filter detail UI")
    public void book_advanced_filter_detail_ui(){
        // Type
        WebElement type = driver.findElement(FilterType);
        Assert.assertEquals(type.getAttribute("placeholder"), "Lọc theo thể loại");
        Assert.assertEquals(type.getAttribute("type"), "text");
        Assert.assertEquals(type.getAttribute("role"), "combobox");
        Assert.assertEquals(type.getAttribute("aria-expanded"), "false");
        System.out.println("Type Pass");

        // Status
        WebElement status = driver.findElement(FilterStatus);
        Assert.assertEquals(status.getAttribute("placeholder"), "Lọc theo trạng thái");
        Assert.assertEquals(status.getAttribute("type"), "text");
        Assert.assertEquals(status.getAttribute("role"), "combobox");
        Assert.assertEquals(status.getAttribute("aria-expanded"), "false");
        System.out.println("Status Pass");

        // Author
        WebElement author = driver.findElement(FilterAuthor);
        Assert.assertEquals(author.getAttribute("placeholder"), "Nhập tên tác giả để tìm kiếm");
        Assert.assertEquals(author.getAttribute("type"), "text");
        Assert.assertEquals(author.getAttribute("role"), "combobox");
        Assert.assertEquals(author.getAttribute("aria-expanded"), "false");
        System.out.println("Author Pass");

        // Sort by
        WebElement sortBy = driver.findElement(FilterSortBy);
        Assert.assertEquals(sortBy.getAttribute("placeholder"), "Sắp xếp theo");
        Assert.assertEquals(sortBy.getAttribute("type"), "text");
        Assert.assertEquals(sortBy.getAttribute("role"), "combobox");
        Assert.assertEquals(sortBy.getAttribute("aria-expanded"), "false");
        System.out.println("Sort by Pass");
    }

    @And("Book filter: Focus input search")
    public void book_filter_focus_input_search(){
        bookSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(InputSearch);
        System.out.println("Pass");
    }

    @And("Book filter: Focus combobox type")
    public void book_filter_focus_combobox_type(){
        bookSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(FilterType);
        System.out.println("Pass");
    }

    @And("Book filter: Focus combobox status")
    public void book_filter_focus_combobox_status(){
        bookSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(FilterStatus);
        System.out.println("Pass");
    }

    @And("Book filter: Focus combobox author")
    public void book_filter_focus_combobox_author(){
        bookSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(FilterAuthor);
        System.out.println("Pass");
    }

    @And("Book filter: Focus combobox sort by")
    public void book_filter_focus_combobox_sort_by(){
        bookSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(FilterSortBy);
        System.out.println("Pass");
    }

    @Then("Book filter: Check focus input search")
    public void book_filter_check_focus_input_search(){
        WebElement border = driver.findElement(By.xpath("//main/div/div/div[1]/div[1]/div"));
        Assert.assertEquals(border.getCssValue("border-color"), "rgb(115, 103, 240)");
        System.out.println("Pass");
    }

    @Then("Book filter: Check focus combobox type")
    public void book_filter_check_focus_combobox_type(){
        // border
        WebElement border = driver.findElement(By.xpath("//main/div/div/div[2]/div/div[1]/div/div/div"));
        Assert.assertEquals(border.getCssValue("border-color"), "rgb(115, 103, 240)");

        // combo box
        WebElement type = driver.findElement(FilterType);
        Assert.assertEquals(type.getAttribute("aria-expanded"), "true");
        System.out.println("Pass");
    }

    @Then("Book filter: Check focus combobox status")
    public void book_filter_check_focus_combobox_status(){
        // border
        WebElement border = driver.findElement(By.xpath("//main/div/div/div[2]/div/div[2]/div/div/div"));
        Assert.assertEquals(border.getCssValue("border-color"), "rgb(115, 103, 240)");

        // combo box
        WebElement status = driver.findElement(FilterStatus);
        Assert.assertEquals(status.getAttribute("aria-expanded"), "true");
        System.out.println("Pass");
    }

    @Then("Book filter: Check focus combobox author")
    public void book_filter_check_focus_combobox_author(){
        // border
        WebElement border = driver.findElement(By.xpath("//main/div/div/div[2]/div/div[3]/div/div/div"));
        Assert.assertEquals(border.getCssValue("border-color"), "rgb(115, 103, 240)");

        // combo box
        WebElement author = driver.findElement(FilterAuthor);
        Assert.assertEquals(author.getAttribute("aria-expanded"), "false");
        System.out.println("Pass");
    }

    @Then("Book filter: Check focus combobox sort by")
    public void book_filter_check_focus_combobox_sort_by(){
        // border
        WebElement border = driver.findElement(By.xpath("//main/div/div/div[2]/div/div[4]/div/div/div"));
        Assert.assertEquals(border.getCssValue("border-color"), "rgb(115, 103, 240)");

        // combo box
        WebElement sortBy = driver.findElement(FilterSortBy);
        Assert.assertEquals(sortBy.getAttribute("aria-expanded"), "true");
        System.out.println("Pass");
    }

    /**
     * Button [Thêm sách] giao diện & action
     */
    @Then("Book: Display button Add book")
    public void book_display_button_add_book(){
        Boolean en_add_book = common.checkDisplay(BtnAddBook);
        Assert.assertEquals(en_add_book, true);
        System.out.println("Pass");
    }

    @Then("Book: Button Add book UI")
    public void book_button_add_book_ui(){
        WebElement add = driver.findElement(BtnAddBook);
        Assert.assertEquals(add.getAttribute("type"), "button");
        Assert.assertEquals(add.getText(), "Thêm sách");
        Assert.assertEquals(add.getCssValue("color"), "rgb(255, 255, 255)");
        Assert.assertEquals(add.getCssValue("background-color"), "rgb(115, 103, 240)");
        Assert.assertEquals(add.getCssValue("border-color"), "rgb(255, 255, 255)");
        Assert.assertEquals(add.getCssValue("font-size"), "16px");
        Assert.assertEquals(add.getCssValue("font-weight"), "400");
        Assert.assertEquals(add.getCssValue("text-align"), "center");
    }

    @And("Book: Click button Add book")
    public void book_click_button_add_book(){
        bookSteps.getInstance().ImplicitlyWait_Config();
        common.clickElement(BtnAddBook);
        common.waitForPageLoaded();
        System.out.println("Pass");
    }

    @Then("Book: Check open Add book screen")
    public void book_check_open_add_book_screen(){
        bookSteps.getInstance().ImplicitlyWait_Config();
        Assert.assertEquals(driver.getCurrentUrl(), "https://book-cms.vmgmedia.vn/content-management/books/create-book");

        WebElement title = driver.findElement(By.xpath("//main/div/div[1]/div[1]"));
        Assert.assertEquals(title.getText(), "Thêm sách mới");
        System.out.println("Pass");
    }

    /**
     * Table - Header
     */
    @Then("Book: Table header display")
    public void book_table_header_display(){
        bookSteps.getInstance().ImplicitlyWait_Config();
        // Sách
        Boolean en_book = common.checkDisplay(Th_Book);
        Assert.assertEquals(en_book, true);

        // Tác giả
        Boolean en_author = common.checkDisplay(Th_Author);
        Assert.assertEquals(en_author, true);

        // Trạng thái
        Boolean en_status = common.checkDisplay(Th_Status);
        Assert.assertEquals(en_status, true);

        // Audio
        Boolean en_audio = common.checkDisplay(Th_Audio);
        Assert.assertEquals(en_audio, true);

        // Gi bán (xu)
        Boolean en_price = common.checkDisplay(Th_CoinPrice);
        Assert.assertEquals(en_price, true);

        // Thao tác
        Boolean en_action = common.checkDisplay(Th_Action);
        Assert.assertEquals(en_action, true);
    }

    @Then("Book: Table header ui")
    public void book_table_header_ui(){
        bookSteps.getInstance().ImplicitlyWait_Config();
        // Sách
        WebElement book = driver.findElement(Th_Book);
        Assert.assertEquals(book.getText(), "Sách");
        Assert.assertEquals(book.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        Assert.assertEquals(book.getCssValue("font-size"), "15px");
        Assert.assertEquals(book.getCssValue("font-weight"), "600");
        Assert.assertEquals(book.getCssValue("display"), "table-cell");
        Assert.assertEquals(book.getCssValue("text-align"), "left");
        System.out.println("Table header: Book pass");

        // Tác giả
        WebElement author = driver.findElement(Th_Author);
        Assert.assertEquals(author.getText(), "Tác giả");
        Assert.assertEquals(author.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        Assert.assertEquals(author.getCssValue("font-size"), "15px");
        Assert.assertEquals(author.getCssValue("font-weight"), "600");
        Assert.assertEquals(author.getCssValue("display"), "table-cell");
        Assert.assertEquals(author.getCssValue("text-align"), "left");
        System.out.println("Table header: Author pass");

        // Trạng thái
        WebElement status = driver.findElement(Th_Status);
        Assert.assertEquals(status.getText(), "Trạng thái");
        Assert.assertEquals(status.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        Assert.assertEquals(status.getCssValue("font-size"), "15px");
        Assert.assertEquals(status.getCssValue("font-weight"), "600");
        Assert.assertEquals(status.getCssValue("display"), "table-cell");
        Assert.assertEquals(status.getCssValue("text-align"), "center");
        System.out.println("Table header: Status pass");

        // Audio
        WebElement audio = driver.findElement(Th_Audio);
        Assert.assertEquals(audio.getText(), "Audio");
        Assert.assertEquals(audio.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        Assert.assertEquals(audio.getCssValue("font-size"), "15px");
        Assert.assertEquals(audio.getCssValue("font-weight"), "600");
        Assert.assertEquals(audio.getCssValue("display"), "table-cell");
        Assert.assertEquals(audio.getCssValue("text-align"), "center");
        System.out.println("Table header: Audio pass");

        // Giá bán (xu)
        WebElement price = driver.findElement(Th_CoinPrice);
        Assert.assertEquals(price.getText(), "Giá bán (xu)");
        Assert.assertEquals(price.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        Assert.assertEquals(price.getCssValue("font-size"), "15px");
        Assert.assertEquals(price.getCssValue("font-weight"), "600");
        Assert.assertEquals(price.getCssValue("display"), "table-cell");
        Assert.assertEquals(price.getCssValue("text-align"), "left");
        System.out.println("Table header: Coin price pass");

        // Thao tác
        WebElement action = driver.findElement(Th_Action);
        Assert.assertEquals(action.getCssValue("color"), "rgba(47, 43, 61, 0.9)");
        Assert.assertEquals(action.getCssValue("text-align"), "center");
        WebElement icon = driver.findElement(By.xpath("//i[@class='tabler-menu-2 w-[22px] h-[22px]']"));
        String icon_actual = "url(\"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' width='24' height='24'%3E%3Cpath fill='none' stroke='black' stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M4 6h16M4 12h16M4 18h16'/%3E%3C/svg%3E\")";
        Assert.assertEquals(icon.getCssValue("--svg"), icon_actual);
        System.out.println("Table header: Action pass");
    }

    /**
     * Lấy token login
     */
    @And("Book API: Get token login")
    public String book_api_get_token_login() throws ParseException, IOException {
        String urlLogin = "https://book-cms-api.vmgmedia.vn/v1/public/auth/login";
        //Dùng cho trường hợp file .json không có biến nào
//        Object body = new JSONParser().parse(new FileReader("src/BookCMS/Data/BodyAPI/bodyLogin.json"));

        // File json có biến
        String filePath = "D:/Tester/BookPlatform_web/src/BookCMS/Data/BodyAPI/infoLogin.json";
        //  classpath chỉ là một đường dẫn để javac và java tìm được đâu là gốc của package, dựa vào đó để tìm các file .java khác.
        JtwigTemplate tempLoginBody = JtwigTemplate.classpathTemplate("./BodyAPI/infoLogin.json"); // lấy theo location của file, nhưng file phải nằm trong 1 package
        JtwigTemplate template = JtwigTemplate.fileTemplate(filePath); // lấy theo path file
        JtwigModel model = JtwigModel.newModel()
                .with("e", email)
                .with("pass", password);
        Object body = template.render(model);

        // Lấy giá trị response api dạng JsonNode, và lấy obj "data" trong response dạng Object
        JsonNode json = common.getToken(urlLogin, body);
        JSONObject data = common.getJsonKey_Obj(json, "data");

        // Lấy giá trị dạng String theo key trong obj "data"
        String token = common.getJsonKey_String(data, "accessToken");
        return token;
    }

    @When("Book API: Data category field")
    public List<String> book_api_data_category_field() throws ParseException, IOException {
        String urlAPI = "https://book-cms-api.vmgmedia.vn/v1/category?";
        String token = book_api_get_token_login();

        JsonNode json = common.getApi(urlAPI, token);
        JSONObject data = common.getJsonKey_Obj(json,"data");
        // Lấy giá trị 1 mảng obj trong 1 obj cha: JSONArray hoặc List<JSONObject>
        JSONArray items = (JSONArray) data.get("items");
        //Khai báo chuỗi kết quả trả về
        List<String> strings = new ArrayList<>();
        for (int i = 0 ; i < items.size() ; i++){
            JSONObject obj = (JSONObject) items.get(i);
            //Lấy tên của categori cha
            String name = (String) obj.get("name");
            strings.add(name);
            // Lấy tên của category con
            JSONArray children = (JSONArray) obj.get("children");
            if (String.valueOf(children) != null){
                for (int j = 0 ; j < children.size() ; j++){
                    JSONObject objChildren = (JSONObject) children.get(j);
                    String nameChildren = (String) objChildren.get("name");
                    strings.add(nameChildren);
                }
            }
        }

        return strings;
    }

    @Then("Book API Result: Check data category field")
    public void book_api_result_check_data_category_field() throws ParseException, IOException {
        List<String> expectData = Arrays.asList("Kỹ năng", "Quản lý", "Tâm lý – Kỹ năng sống", "Nuôi dạy con", "Kỹ năng mềm", "Marketing – Bán hàng",
                "Văn học", "Tiểu thuyết", "Trinh thám", "Truyện ngắn",
                "Chưa phân loại");

        List<String> actualData = book_api_data_category_field();

        Assert.assertEquals(actualData, expectData);
    }


}
