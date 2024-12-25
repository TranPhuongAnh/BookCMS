package learnTest;

public class TinhDongGoi {
    private String name;
/* Ví dụ thực tế cho áp dụng auto test
    public setText(WebElement element, String value){
        element.sendKeys(value);
    }

    public getText(WebElement element){
        return element.getText();
    }
*/
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public static void main(String[] args) {
        TinhDongGoi dongGoi = new TinhDongGoi();
        dongGoi.setName("PA");
        System.out.println("Tên của bạn: " + dongGoi.getName());
    }
}
