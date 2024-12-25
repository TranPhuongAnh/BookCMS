package learnTest;

//Nạp chồng: tạo ra nhiều hàm có tên hàm giống nhau nhưng khác nhau về kiểu dữ liệu hoặc đối số bên trong hàm
class Adder{
    static int add(int a){
        return a;
    }

    /**2 hàm có kiểu dữ liệu và số đối số giống nhau:
     * Khác hoàn toàn về kiểu dữ liệu của đối số thì có thể nạp chồng (các hàm 1-3 / 2-3)
     * Chỉ khác tên kiểu dữ liệu nhưng vẫn chung 1 kiểu dữ liệu lớn của đối số thì không thể nạp chồng (hàm 1-2 không thể nạp chồng)
    */
    static void add(long a, int b){
        long s = a+ b;
        System.out.println("Hàm 1 add(long a, int b) có giá trị: " + s);
    }
    static void add(int a, long b){
        long s = a+ b;
        System.out.println("Hàm 2 add(int a, long b) có giá trị: " + s);
    }
    static void add(String a, long b){
        String s = a+ b;
        System.out.println("Hàm 3 add(String a, long b) có giá trị: " + s);
    }

    static String add(String a, int b, String c){
        return a + b + c;
    }
}

public class NapChong {
    //Không thể ghi đè hàm main()
    public static void main(String[] args) {
        Adder a = new Adder();
        System.out.println("Tính nạp chồng:");
        System.out.println("Hàm add(int a) có giá trị: " + Adder.add(56));
        //a.add("PA", 1);
        //a.add(6,5);
        System.out.println("Hàm add(String a, int b, String C) có giá trị: " + Adder.add("PA ", 4, " test thôi"));
    }

    //Có thể nạp chồng hàm main(), nhưng phải đổi kiểu dữ liệu của đối số từ String[] thành String, Int, Float....
    public static void main(String args) {

    }
}
