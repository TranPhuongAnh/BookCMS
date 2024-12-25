package KeThua;
import objTest.*;

public class InheritanceClass extends PhamViTruyCap{ //bắt buộc kế thừa public class, 1 file chỉ có 1 class public

    public static void main(String[] args) {
        //Khởi tạo obj chỉ cần khai báo import package, mà k cần kế thừa
        InheritanceClass prot = new InheritanceClass();
        //Không cùng 1 package, phải kế thừa mới gọi được hàm protected
        prot.testProtect();
        prot.testDefault();
        prot.testPrivate();

    }
}
