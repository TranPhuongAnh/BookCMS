package learnTest;

public class ArrayClass {
    public static void main(String[] args) {
        //nặc danh
        int a[] = {1, 4, 6, 78, 45, 67, 234, 65};
        for (int i: a){
            System.out.println(i);
        }

        String arr[] = new String[10];
        arr[0] = "abc";
        arr[2] = "Ten toi la";
        arr[4] = "Phuong Anh";
        arr[7] = "10";
        arr[9] = "3";

        for(int i = 0; i < arr.length ; i++){
            if(i == 2 || i == 4) {
                System.out.println(arr[2] + ": " + arr[4] + ". Day la vi tri" + i);
            } else if (arr[i] == null){
                arr[i] = "PA" + i;
            }
            System.out.println("Vi tri "+ i + " co gia tri: " + arr[i]);
        }

        int num1 = Integer.valueOf(arr[7]).intValue();
        int num2 = Integer.valueOf(arr[9]).intValue();
        System.out.println("Tong so trong mang: " + num1 + num2);
    }
}
