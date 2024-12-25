package DaKeThua;

import KeThua.InheritanceStaff;

import java.time.LocalDate;

public class MultipleInheritance extends InheritanceStaff {
    public static void main(String[] args) {
        MultipleInheritance mIhr = new MultipleInheritance();
        mIhr.Info("PA", LocalDate.of(1993, 6, 18), "Nữ");
        mIhr.PrtInfo();
        System.out.println("Số ngày làm việc: " + mIhr.Work(23, 1));
        System.out.println("Lương tháng của nhân viên: " + mIhr.Salary(6000000));

        //NV2
        mIhr.Info("Mai Kim Thuý", LocalDate.of(2003, 2, 11), "Nữ");
        mIhr.PrtInfo();
        System.out.println("Số ngày làm việc: " + mIhr.Work(23, 2));
        System.out.println("Lương tháng của nhân viên: " + mIhr.Salary(5200000));
    }
}
