package objTest;

class TestExample{ //Class không được cấp bộ nhớ, phải tự tạo logic code
    public int num1 = 200;
    public int num2 = 300;

    public int Cong(int a, int b){
        return a+b;
    }
    public void PrtCong(int a, int b){
        System.out.println(Cong(a, b));
    }
}

public class ObjAndClass {

    public static void main() {
        //Khởi tạo 1 obj tạo bộ nhớ theo class đang khởi tạo với new, bộ nhớ chứa code trong class
        TestExample exp = new TestExample();
        exp.PrtCong(exp.num1, exp.num2);

        PhamViTruyCap pvtc = new PhamViTruyCap();
        //Cùng package không cần kế thừa vẫn gọi được hàm protected
        pvtc.testProtect();
    }
}
