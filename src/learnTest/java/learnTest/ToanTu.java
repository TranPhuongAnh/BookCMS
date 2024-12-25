package learnTest;

public class ToanTu {
    static int a = 10;
    static int b = 32;
    static float c = 3.114F;
    static boolean d = true;

    public void TinhToan(){
        if (a > 10 && b < 40){
            a += b; //a = a + b
            System.out.println("Tong a & b la " + a);
        } else if (a == 110 || b < 30){
            int e = a-b;
            System.out.println("a - b la " + e);
        } else if (a < 10 || b > 310){
            System.out.println("Th 3 có c la:" + c);
        }
        else System.out.println("Khong truong hop nao dung: " + d);
    }
    public static void main(String[] args) {
        if (a > 10 && b < 40){
            a += b; //a = a + b
            System.out.println("Tong a & b la " + a);
        } else if (a == 110 || b < 30){
            int e = a-b;
            System.out.println("a - b la " + e);
        } else if (a < 10 || b > 310){
            System.out.println("Th 3 có c la:" + c);
        }
        else System.out.println("Khong truong hop nao dung: " + d);

//        String MessageDigest = element_value.getText();
//        if(MessageDigest == "Cập nhật thành công."){
//
//        } else
    }
}
