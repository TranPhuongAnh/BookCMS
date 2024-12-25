package objTest;
import learnTest.*;
import learnTest.Function;

public class RunCode {
    public static void main(String[] args) {
        // Chỉ có thể gọi các hàm public

        ToanTu toan = new ToanTu();
        toan.TinhToan();

        Function vari = new Function();
        int[] arr = new int[10];
        vari.InputData(arr, 6);
        vari.PrtData(arr, 7);

        //Hàm không public gọi lỗi
//        vari.test(arr, 9);
//        vari.test2(arr, 6);

        ObjAndClass obj = new ObjAndClass();
        TestExample test = new TestExample();
        test.PrtCong(test.num1, test.num2);

    }
}
