package objTest;

class PrivateClass{
    //private: chỉ được dùng trong cùng 1 class, khác class không thể dùng
    private int a = 90;
    void priv(){
        System.out.println("Hàm private có giá trị: " + a);
    }
}

class DefaultClass{
    //default: chỉ được dùng trong cùng 1 package
    int a = 60;
    void deft(){
        System.out.println("Hàm default có giá trị: " + a);
    }
}

class ProtectedClass{
    //protected: được truy cập tự do trong cùng 1 package, ngoài package phải kế thừa
    protected int a = 70;
}
//public: được truy cập ở bất cứ đâu
public class PhamViTruyCap {
    protected void testProtect(){
        ProtectedClass p = new ProtectedClass();
        System.out.println("Hàm protected có giá trị: " + p.a);
    }
    protected void testPrivate(){
        PrivateClass p = new PrivateClass();
        p.priv();
    }
    protected void testDefault(){
        DefaultClass p = new DefaultClass();
        p.deft();
    }

    public static void main(String[] args) {
        PrivateClass pri = new PrivateClass();
        pri.priv();
    }
}
