package KeThua;

import objTest.Staff;

import java.time.LocalDate;

public class InheritanceStaff extends Staff {
    public static void main(String[] args) {
        InheritanceStaff ihr = new InheritanceStaff();
        //NV1
        ihr.Info("PA", LocalDate.of(1993, 6, 18), "Nữ");
        ihr.PrtInfo();
        System.out.println("Số ngày làm việc: " + ihr.Work(23, 1));
        System.out.println("Lương tháng của nhân viên: " + ihr.Salary(6000000));

        //NV2
        ihr.Info("Mai Kim Thuý", LocalDate.of(2003, 2, 11), "Nữ");
        ihr.PrtInfo();
        System.out.println("Số ngày làm việc: " + ihr.Work(23, 2));
        System.out.println("Lương tháng của nhân viên: " + ihr.Salary(5200000));

        // Pass
        String p = ihr.Password("ghjkshdh", "·");
        System.out.println("Pass hiển thị: " + p);

    }
}
