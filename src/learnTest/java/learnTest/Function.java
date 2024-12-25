package learnTest;
import objTest.*;

public class Function {
   public static void InputData(int arr[], int num){
        int a = arr[0];
        for (int i = 0 ; i < arr.length ; i++){
            arr[i] = num;
        }
    }
    public static void PrtData(int arr[], int num){
        int a = arr[0];
        for (int i = 0 ; i < arr.length ; i++){
            System.out.println("Gia tri cua phan tu thu " + (i+1) + " la " + (arr[i] + num));
        }
    }

    //Ham khong tra ve
    static void test(int arr[], int num){
        for (int i = 0 ; i < arr.length ; i++){
            if (num > arr[i]){
                arr[i] = num;
            }
        }
    }
    //Ham tra ve
    static int test2(int arr[], int num){
        for (int i = 0 ; i < arr.length ; i++){
            if (num > arr[i]){
                arr[i] = num;
                return arr[i];
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        int array[] = new int[10];
        InputData(array, 7);
//        PrtData(array, 6);

        int array2[] = new int[10];
        InputData(array2, 30);
//        PrtData(array2, 9);

        //Ham k tra ve
//        int b = test(array, 3);

        //Ham tra ve
        int c = test2(array2, 4);
        System.out.println(c);

        //Khi package khác nhau, nếu class không public, không thể khỏi tạo obj cho class
//        TestExample e = new TestExample();
        ObjAndClass o = new ObjAndClass();
        o.main();
    }
}
