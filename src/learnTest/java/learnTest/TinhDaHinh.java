package learnTest;

class Bike{
    void run(){
        System.out.println("Xe đạp chạy tối đa 2km/h.");
    }
    void stop(){
        System.out.println("Dừng lại!!!");
    }
}

public class TinhDaHinh extends Bike{
    //Nhiều hàm tên giống nhau tại nhiều class
    void run(){
        System.out.println("Xe máy chạy tối đa 120km/h.");
    }
    void stopNow(){
        System.out.println("Dừng lại ngay bây giờ!!!");
    }

    public static void main(String[] args) {
        //Khai báo đối tượng tại class cha (Bike) thì chỉ được phép gọi các hàm ở class cha (Bike)
        Bike b = new Bike();
        b.run();

        //Khai báo đối tượng của class cha (Bike) tại class con (TinhDaHinh)
        Bike bk = new TinhDaHinh();
        /**Do tên hàm trùng nhau nên khi khai báo đối tượng tại class con (TinhDaHinh) thì đối tượng trỏ được hàm ở class con (TinhDaHinh)
         * Ghi đè có tính tương tự đa hình
         * Ghi đè nằm trong tính Nạp chồng
         */
        bk.run();

        //Khi tên hàm không trùng nhau thì khai báo đối tượng tại class con (TinhDaHinh) thì đối tượng sẽ trỏ được các hàm ở class cha (Bike)
        bk.stop();

        //Hàm chỉ ở class con (TinhDaHinh), khi khai báo đối tượng tại/của class cha (Bike) sẽ không trỏ được
//        b.stopNow();
//        bk.stopNow();

        //Hàm chỉ ở tại class con (TinhDaHinh), khi khai báo đối tượng tại class con (TinhDaHinh) thì trỏ được
        TinhDaHinh t = new TinhDaHinh();
        t.stopNow();
    }
}
